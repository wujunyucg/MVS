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
				list = new LineDaoImpl().getAllLine(con);
				ArrayList<JSONObject> json_linelist = new ArrayList<JSONObject>();  
				ArrayList<JSONObject> json_sitelist = new ArrayList<JSONObject>();  
				ArrayList<JSONObject> json_allsite = new ArrayList<JSONObject>();  
				
				for(int i=0;i<list.size();i++){
					DecimalFormat df = new DecimalFormat( "0.00000 ");  
					list.get(i).setRate(Double.valueOf(df.format(list.get(i).getRate())).doubleValue());
					String temp1 = new String();
					temp1 = new Lines().getSitesNameByIds(list.get(i).getSiteId(), con);
					
					ArrayList<Site> sitelist = new ArrayList<Site>();
					sitelist = new Lines().getSiteListByIds(list.get(i).getSiteId(), con);
					
					json_sitelist.add(new JSONObject());
					json_sitelist.get(i).put("sitelist", sitelist);  
					
					list.get(i).setSiteId(temp1);
					
					json_linelist.add(new JSONObject());
					json_linelist.get(i).put("linelist", list.get(i));  
					
				}
				ArrayList<Site> allsite = null;
				allsite = new SiteDaoImpl().getAllSite(con);
				for(int j=0;j<allsite.size();j++){
					json_allsite.add(new JSONObject());
					json_allsite.get(j).put("allsite", allsite.get(j));  
					
				}
				Site fac = null;
				fac = new SiteDaoImpl().getSiteById(0, con);
				request.getSession().setAttribute("fac_latitude",fac.getLatitude());
				request.getSession().setAttribute("fac_longitude",fac.getLongitude());
				request.getSession().setAttribute("linelist",list);
				request.getSession().setAttribute("json_sitelist",json_sitelist.toString());
				request.getSession().setAttribute("json_linelist",json_linelist.toString());
				request.getSession().setAttribute("json_allsite",json_allsite.toString());
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
				
				Site fac_site = new SiteDaoImpl().getSiteById(0, con);
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
						
						try {
							new LineDaoImpl().addMoreLine(linelist, con);
							new Lines().addLineOfSite(con);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						ArrayList<Line> list = null;
						list = new LineDaoImpl().getAllLine(con);
						ArrayList<JSONObject> json_linelist = new ArrayList<JSONObject>();  
						ArrayList<JSONObject> json_sitelist = new ArrayList<JSONObject>();  
						ArrayList<JSONObject> json_allsite = new ArrayList<JSONObject>();  
						for(int i=0;i<list.size();i++){
							DecimalFormat df = new DecimalFormat( "0.00000 ");  
							list.get(i).setRate(Double.valueOf(df.format(list.get(i).getRate())).doubleValue());
							String temp1 = new String();
							temp1 = new Lines().getSitesNameByIds(list.get(i).getSiteId(), con);

							ArrayList<Site> sitelist1 = new ArrayList<Site>();
							sitelist1 = new Lines().getSiteListByIds(list.get(i).getSiteId(), con);
							
							json_sitelist.add(new JSONObject());
							json_sitelist.get(i).put("sitelist", sitelist1);  
							
							list.get(i).setSiteId(temp1);
							
							json_linelist.add(new JSONObject());
							json_linelist.get(i).put("linelist", list.get(i));  
						}
						ArrayList<Site> allsite = null;
						allsite = new SiteDaoImpl().getAllSite(con);
						for(int j=0;j<allsite.size();j++){
							json_allsite.add(new JSONObject());
							json_allsite.get(j).put("allsite", allsite.get(j));  
						}
						
						String json_s  = json_linelist.toString() + "&" + json_sitelist.toString() + "&" + json_allsite.toString();
						System.out.println("$"+json_linelist);
						pw.write(json_s);
						
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
				Line line  = null;
				line = new LineDaoImpl().getLineById(con, lineid);
				if(line.getRate() < 0.0 && line.getRate() != 0.0){	//智能路线的删除
					new Lines().deleteIntelLine(line,con);
				}else{			//手动创建路线的删除
					new Lines().deleteOneLine(line, con);
				}
				
				ArrayList<Line> list = null;
				try {
					list = new LineDaoImpl().getAllLine(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ArrayList<JSONObject> json_linelist = new ArrayList<JSONObject>();  
				ArrayList<JSONObject> json_sitelist = new ArrayList<JSONObject>();  
				ArrayList<JSONObject> json_allsite = new ArrayList<JSONObject>();  
				for(int i=0;i<list.size();i++){
					DecimalFormat df = new DecimalFormat( "0.00000 ");  
					list.get(i).setRate(Double.valueOf(df.format(list.get(i).getRate())).doubleValue());
					String temp1 = new String();
					temp1 = new Lines().getSitesNameByIds(list.get(i).getSiteId(), con);

					ArrayList<Site> sitelist = new ArrayList<Site>();
					sitelist = new Lines().getSiteListByIds(list.get(i).getSiteId(), con);
					
					json_sitelist.add(new JSONObject());
					json_sitelist.get(i).put("sitelist", sitelist);  
					
					list.get(i).setSiteId(temp1);
					
					json_linelist.add(new JSONObject());
					json_linelist.get(i).put("linelist", list.get(i));  
				}
				ArrayList<Site> allsite = null;
				allsite = new SiteDaoImpl().getAllSite(con);
				for(int j=0;j<allsite.size();j++){
					json_allsite.add(new JSONObject());
					json_allsite.get(j).put("allsite", allsite.get(j));  
				}
				System.out.println("+"+json_linelist.toString());
				System.out.println("+"+json_sitelist.toString());
				System.out.println("+"+json_allsite.toString());
				String json_s  = json_linelist.toString() + "&" + json_sitelist.toString() + "&" + json_allsite.toString();
				pw.write(json_s);
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
				
				try {
					new LineDaoImpl().addMoreLine(linelist, con);
					new Lines().addMoreLineOfSite(linelist,con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ArrayList<Line> list = null;
				list = new LineDaoImpl().getAllLine(con);
				ArrayList<JSONObject> json_linelist = new ArrayList<JSONObject>();  
				ArrayList<JSONObject> json_sitelist = new ArrayList<JSONObject>();  
				ArrayList<JSONObject> json_allsite = new ArrayList<JSONObject>();  
				for(int i=0;i<list.size();i++){
					DecimalFormat df = new DecimalFormat( "0.00000 ");  
					list.get(i).setRate(Double.valueOf(df.format(list.get(i).getRate())).doubleValue());
					String temp1 = new String();
					temp1 = new Lines().getSitesNameByIds(list.get(i).getSiteId(), con);

					ArrayList<Site> sitelist1 = new ArrayList<Site>();
					sitelist1 = new Lines().getSiteListByIds(list.get(i).getSiteId(), con);
					
					json_sitelist.add(new JSONObject());
					json_sitelist.get(i).put("sitelist", sitelist1);  
					
					list.get(i).setSiteId(temp1);
					
					json_linelist.add(new JSONObject());
					json_linelist.get(i).put("linelist", list.get(i));  
				}
				ArrayList<Site> allsite = null;
				allsite = new SiteDaoImpl().getAllSite(con);
				for(int j=0;j<allsite.size();j++){
					json_allsite.add(new JSONObject());
					json_allsite.get(j).put("allsite", allsite.get(j));  
				}
				String json_s  = json_linelist.toString() + "&" + json_sitelist.toString() + "&" + json_allsite.toString();
				pw.write(json_s);
			}
			else if(type.equals("7")){	//判断路线是否重名
				String line_name = request.getParameter("line_name");
				String line_id = request.getParameter("line_id");
				if(Integer.valueOf(line_id).intValue() == -1){
					ArrayList<Line> linelist = new ArrayList<Line>();
					linelist = new LineDaoImpl().getAllLine(con);
					for(int i=0;i<linelist.size();i++){
						if(linelist.get(i).getName().equals(line_name)){
							pw.write("no");
							pw.close();
							break;
						}
					}
					if(line_name.contains("&")||line_name.contains("_")||line_name.contains("Z")){
						pw.write("no");
						pw.close();
					}
					pw.write("yes");
				}else{
					ArrayList<Line> linelist = new ArrayList<Line>();
					linelist = new LineDaoImpl().getAllLine(con);
					for(int i=0;i<linelist.size();i++){
						if(linelist.get(i).getName().equals(line_name)&&linelist.get(i).getLineId()!=Integer.valueOf(line_id).intValue()){
							pw.write("no");
							pw.close();
							break;
						}
					}
					if(line_name.contains("&")){
						pw.write("no");
						pw.close();
					}
					pw.write("yes");
				}
			}
			else if(type.equals("8")){		//手动创建线路
				String line_name = request.getParameter("line_name");
				String siteId = request.getParameter("siteId");
				siteId += ",0";
				String[] site_ids = siteId.split(",");
				String peoNum = request.getParameter("peoNum");
				if(peoNum.equals("预计人数请创建后查看")){			//重复站点路线
					Line line = new Line();
					line.setCarId("未安排");
					line.setName(line_name);
					line.setNum(-1);
					line.setRate(0.0);
					line.setSiteId(siteId);
					new LineDaoImpl().addLine(line, con);
					Line temp_line = null;
					temp_line = new LineDaoImpl().getLineByName(con, line.getName());
					line.setLineId(temp_line.getLineId());
					new Lines().modifyLineNum(line,con);
				}else{	//无重复站点路线
					Line line = new Line();
					line.setCarId("未安排");
					line.setName(line_name);
					int peonumber = 0;
					for(int i=0;i<site_ids.length;i++){
						Site site = new Site();
						 site = new SiteDaoImpl().getSiteById(Integer.valueOf(site_ids[i]).intValue(), con);
						 peonumber += site.getPeoNum();
					}
					line.setNum(peonumber);
					line.setRate(0.0);
					line.setSiteId(siteId);
					new LineDaoImpl().addLine(line, con);
					
					Line line_t = new Line();
					line_t = new LineDaoImpl().getLineByName(con, line_name);
					System.out.println("line_t"+line_t.getLineId());
					new Lines().haddLineOfSite(line_t,con);
				}
				
				ArrayList<Line> list = null;
				list = new LineDaoImpl().getAllLine(con);
				ArrayList<JSONObject> json_linelist = new ArrayList<JSONObject>();  
				ArrayList<JSONObject> json_sitelist = new ArrayList<JSONObject>();  
				ArrayList<JSONObject> json_allsite = new ArrayList<JSONObject>();  
				for(int i=0;i<list.size();i++){
					DecimalFormat df = new DecimalFormat( "0.00000 ");  
					list.get(i).setRate(Double.valueOf(df.format(list.get(i).getRate())).doubleValue());
					String temp1 = new String();
					temp1 = new Lines().getSitesNameByIds(list.get(i).getSiteId(), con);

					ArrayList<Site> sitelist = new ArrayList<Site>();
					sitelist = new Lines().getSiteListByIds(list.get(i).getSiteId(), con);
					
					json_sitelist.add(new JSONObject());
					json_sitelist.get(i).put("sitelist", sitelist);  
					
					list.get(i).setSiteId(temp1);
					
					json_linelist.add(new JSONObject());
					json_linelist.get(i).put("linelist", list.get(i));  
				}
				ArrayList<Site> allsite = null;
				allsite = new SiteDaoImpl().getAllSite(con);
				for(int j=0;j<allsite.size();j++){
					json_allsite.add(new JSONObject());
					json_allsite.get(j).put("allsite", allsite.get(j));  
				}
				
				String json_s  = json_linelist.toString() + "&" + json_sitelist.toString() + "&" + json_allsite.toString();
				pw.write(json_s);
			}
			else if(type.equals("9")){		//修改路线
				String line_id = request.getParameter("line_Id");
				String line_name = request.getParameter("line_name");
				String siteId = request.getParameter("siteId");
				siteId += ",0";
					
				Line line = new Line();
				line.setCarId("未安排");
				line.setName(line_name);
				line.setNum(-1);
				line.setRate(0.0);
				line.setSiteId(siteId);
				line.setLineId(Integer.valueOf(line_id).intValue());
				
				Line delete_line = null;
				delete_line = new LineDaoImpl().getLineById(con, Integer.valueOf(line_id).intValue());
				
				if(delete_line.getSiteId().equals(siteId)){
					delete_line.setCarId("未安排");
					String oldname = delete_line.getName();
					delete_line.setName(line_name + "_Z");
					new LineDaoImpl().updateLine(con, delete_line);
					new Lines().modifyLineName(oldname,delete_line.getLineId(),con);
				}else{
					if(delete_line.getRate() < 0.0 && delete_line.getRate() != 0.0){	//智能路线的删除
						new Lines().deleteIntelLine(delete_line,con);//智能路线的一连串删除
					}else{			//手动创建路线的删除
						new Lines().deleteOneLine(delete_line, con);
					}
					new LineDaoImpl().addLineAndId(line, con);
					Line temp_line = null;
					temp_line = new LineDaoImpl().getLineByName(con, line.getName());
					line.setLineId(temp_line.getLineId());
					new Lines().modifyLineNum(line,con);
				}
				

				/*JSON数据返回*/
				ArrayList<Line> list = null;
				list = new LineDaoImpl().getAllLine(con);
				ArrayList<JSONObject> json_linelist = new ArrayList<JSONObject>();  
				ArrayList<JSONObject> json_sitelist = new ArrayList<JSONObject>();  
				ArrayList<JSONObject> json_allsite = new ArrayList<JSONObject>();  
				for(int i=0;i<list.size();i++){
					DecimalFormat df = new DecimalFormat( "0.00000 ");  
					list.get(i).setRate(Double.valueOf(df.format(list.get(i).getRate())).doubleValue());
					String temp1 = new String();
					temp1 = new Lines().getSitesNameByIds(list.get(i).getSiteId(), con);

					ArrayList<Site> sitelist = new ArrayList<Site>();
					sitelist = new Lines().getSiteListByIds(list.get(i).getSiteId(), con);
					
					json_sitelist.add(new JSONObject());
					json_sitelist.get(i).put("sitelist", sitelist);  
					
					list.get(i).setSiteId(temp1);
					
					json_linelist.add(new JSONObject());
					json_linelist.get(i).put("linelist", list.get(i));  
				}
				ArrayList<Site> allsite = null;
				allsite = new SiteDaoImpl().getAllSite(con);
				for(int j=0;j<allsite.size();j++){
					json_allsite.add(new JSONObject());
					json_allsite.get(j).put("allsite", allsite.get(j));  
				}
				
				String json_s  = json_linelist.toString() + "&" + json_sitelist.toString() + "&" + json_allsite.toString();
				pw.write(json_s);
			}
			else if(type.equals("10")){
				new Lines().modifyLineOfStaff(con);
				
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
