package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.intelligent.PlanRoute;
import edu.swjtu.model.Car;
import edu.swjtu.model.Line;
import edu.swjtu.model.Lines;
import edu.swjtu.model.Site;
import edu.swjtu.util.DBUtil;

public class ManageLineServlet extends HttpServlet {
	DBUtil db = new DBUtil();
	
	public ManageLineServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");  
		Connection con = null;
		PrintWriter pw = response.getWriter();
		
		try {
			con = db.getCon();
		
			
			String type = request.getParameter("type");
			if(type.equals("1")){
				ArrayList<Line> prelist = null;
				prelist = new LineDaoImpl().getAllLine(con);
				new Lines().deleteIllegalLine(prelist, con);
				
				ArrayList<Line> list = null;
				try {
					list = new LineDaoImpl().getAllLine(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ArrayList<String> siteNames = new ArrayList<String> ();
				ArrayList<String> carNumbers = new ArrayList<String> ();
				ArrayList<String> onesite = new ArrayList<String> ();
				ArrayList<JSONObject> jsonObject = new ArrayList<JSONObject>();  
				for(int i=0;i<list.size();i++){
					DecimalFormat df = new DecimalFormat( "0.00000 ");  
					list.get(i).setRate(Double.valueOf(df.format(list.get(i).getRate())).doubleValue());
					String temp1 = new String();
					try {
						temp1 = new Lines().getSitesNameByIds(list.get(i).getSiteId(), con);
					} catch (NumberFormatException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					siteNames.add(temp1);
					String temp2 = new Lines().getCarNumByIds(list.get(i).getCarId(), con);
					carNumbers.add(temp2);
					
					ArrayList<Site> sitelist = new ArrayList<Site>();
					try {
						sitelist = new Lines().getSiteListByIds(list.get(i).getSiteId(), con);
					} catch (NumberFormatException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JSONObject temp3 = new JSONObject();
					jsonObject.add(temp3);
					jsonObject.get(i).put("sitelist", sitelist);  
					String temp4 = new String();
					temp4 = jsonObject.get(i).toString();
					onesite.add(temp4);
				}
				ArrayList<Site> allsitelist = new ArrayList<Site>();
				allsitelist = new SiteDaoImpl().getAllSite(con);
				JSONObject jsonObject_s = new JSONObject();  
		        jsonObject_s.put("sitelist", allsitelist);  
		        String temp_sitejson = "[" +jsonObject_s.toString()  + "]";
		        System.out.println(jsonObject_s.toString());
		        System.out.println(temp_sitejson);
		        System.out.println(jsonObject.toString());
		        System.out.println(onesite);
		        request.getSession().setAttribute("json_allsite", jsonObject_s.toString());
		        request.getSession().setAttribute("json_allline", jsonObject.toString());
		        request.getSession().setAttribute("json_oneline", onesite);
	//	        System.out.println("*****");
	//	        System.out.println( jsonObject.toString());
				request.getSession().setAttribute("linelist",list);
				request.getSession().setAttribute("siteNames", siteNames);
				request.getSession().setAttribute("carNumbers", carNumbers);
		        request.getRequestDispatcher("../jsp_user/map_line.jsp").forward(request, response);		
			}
			else if(type.equals("2")){	//完全智能规划路线
				String min_rec = request.getParameter("min_rec");
				String max_len = request.getParameter("max_len");
				double minRec = Double.valueOf(min_rec).doubleValue();
				minRec = minRec * 0.01;
				if(minRec == 0.0){
					minRec = 0.000000001;
				}
				double maxLen = Double.valueOf(max_len).doubleValue();
				ArrayList<Site> sitelist = new ArrayList<Site>();
				sitelist = new SiteDaoImpl().getAllSite(con);
				ArrayList<Car> carlist = new ArrayList<Car>();
				carlist = new CarDaoImpl().getAllCar(con);
				
				Collections.sort(carlist, new Comparator<Car>() {
		            @Override
		            public int compare(Car c1, Car c2) {
		                Integer num1 = c1.getNumber();
		                Integer num2 = c2.getNumber();
		                return num2.compareTo(num1);
		            }
				});
				
				Site fac_site = new Site();
				fac_site.setLatitude(30.655826);
				fac_site.setLongitude(104.065349);
				int proces = 0;
				PlanRoute pr = new PlanRoute();
				pr.pro = 0;
				request.getSession().removeAttribute("pr");
				if(request.getSession().getAttribute("pr")==null){
					System.out.println("nullll");
				}else{
					System.out.println("not null");
				}
				try {
					if(new LineDaoImpl().deleteAllLine(con) >= 0){
						new Lines().deleteLineOfSite(con);
						ArrayList<Line> linelist = new ArrayList<Line>();
						request.getSession().setAttribute("pr",pr);
						
						try{
							linelist = pr.intelligentLine(minRec, maxLen, sitelist,carlist, fac_site, 0, 0);
						}catch(Exception e){
							pw.write("no");
							pw.close();
						}
						
						pw.write("yes");
						try {
							new LineDaoImpl().addMoreLine(linelist, con);
							new Lines().addLineOfSite(con);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(type.equals("3")){
				String min_rec = request.getParameter("min_rec");
				
				try {  		
					double minrec = Double.valueOf(min_rec).doubleValue();
					if(minrec >=0.0 && minrec <= 100.0){
						pw.write("yes");
					}
					else{
						pw.write("no");
					}
		        } catch (NumberFormatException e) {  
		        	pw.write("no");
				}
				
			}
			else if(type.equals("4")){
				String max_len = request.getParameter("max_len");
				try {  		
					double maxlen = Double.valueOf(max_len).doubleValue();
					if(maxlen >=0.0){
						pw.write("yes");
					}
					else{
						pw.write("no");
					}
		        } catch (NumberFormatException e) {  
		        	pw.write("no");
				}
			}
			else if(type.equals("5")){	//删除路线
				int lineid = Integer.valueOf(request.getParameter("lineId")).intValue();
				new Lines().deleteOneLine(lineid, con);
				int u = new LineDaoImpl().deleteLine(lineid, con);
				if(u!=0){
					pw.write("yes");
				}else{
					pw.write("no");
				}
			}
			else if(type.equals("6")){	//部分智能线路规划——只设计未规划线路的站点集合
				String min_rec = request.getParameter("min_rec");
				String max_len = request.getParameter("max_len");
				double minRec = Double.valueOf(min_rec).doubleValue();
				minRec = minRec * 0.01;
				if(minRec == 0.0){
					minRec = 0.000000001;
				}
				double maxLen = Double.valueOf(max_len).doubleValue();
				ArrayList<Site> sitelist = new ArrayList<Site>();
				sitelist = new Lines().getNotSiteList(con);	
				ArrayList<Car> carlist = new ArrayList<Car>();
				carlist = new CarDaoImpl().getAllCar(con);
				
				Collections.sort(carlist, new Comparator<Car>() {
		            @Override
		            public int compare(Car c1, Car c2) {
		                Integer num1 = c1.getNumber();
		                Integer num2 = c2.getNumber();
		                return num2.compareTo(num1);
		            }
				});
				
				Site fac_site = new Site();
				fac_site.setLatitude(30.655826);
				fac_site.setLongitude(104.065349);
				int proces = 0;
				PlanRoute pr = new PlanRoute();
				pr.pro = 0;
				request.getSession().removeAttribute("pr");
				if(request.getSession().getAttribute("pr")==null){
					System.out.println("nullll");
				}else{
					System.out.println("not null");
				}
				ArrayList<Line> linelist = new ArrayList<Line>();
				request.getSession().setAttribute("pr",pr);
				
				try{
					linelist = pr.intelligentLine(minRec, maxLen, sitelist,carlist, fac_site, 0, 0);
				}catch(Exception e){
					pw.write("no");
					pw.close();
				}
				
				pw.write("yes");
				try {
					new LineDaoImpl().addMoreLine(linelist, con);
					new Lines().addMoreLineOfSite(linelist,con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			if(null!=con){
				try {
					db.closeCon(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public void init() throws ServletException {
	}

}
