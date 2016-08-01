package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.mail.internet.NewsAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONBuilder;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.SiteRecordDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Site;
import edu.swjtu.model.SiteRecord;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;

/**
 * 
 * ShowSiteServlet.java类
 * 2016年8月1日
 * @author wujunyu
 * TODo
 */
public class ShowSiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShowSiteServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		String date = request.getParameter("date");
		PrintWriter out = response.getWriter();
		DBUtil db =new DBUtil();
		try {
			Connection con = db.getCon();
			SiteRecordDaoImpl srdi = new SiteRecordDaoImpl();
			SiteDaoImpl sdi = new SiteDaoImpl();
			StaffDaoImpl sdi1 = new StaffDaoImpl();
			if(type.equals("day")){
				ArrayList<SiteRecord> srList = srdi.getSiteRecordByDate(1, date, con);
				ArrayList<Site> siteList = new ArrayList<Site>();
				for(SiteRecord  sr :srList){
					Site site = sdi.getSiteById(sr.getSiteId(), con);
					siteList.add(site);
				}
				JSONObject jo =new JSONObject();
				jo.put("site_list", siteList);
				jo.put("sr_list", srList);
				out.print(jo.toString());
			}
			else if(type.equals("week")){
				ArrayList<SiteRecord> srList = srdi.getSiteRecordByDate(2, date, con);
				String []week=srdi.dayForWeek(date);
				ArrayList<SiteRecord> srList1 = new ArrayList<SiteRecord>();
				 for (int i = 0; i < srList.size()-1; i++) {  
	            		for (int j = srList.size()-1; j > i; j--){
	            			
	            			if(srList.get(i).getSiteId()==srList.get(j).getSiteId()){
	            				srList.get(i).setNum(srList.get(i).getNum()+srList.get(j).getNum());
	            				HashSet<String> set = new HashSet<String>();
	            				String [] ids =srList.get(i).getStaffIds().split(",");
	            				for(int k=0;k<ids.length;k++){
	            					set.add(ids[k]);
	            				}
	            				 ids =srList.get(j).getStaffIds().split(",");
	            				for(int k=0;k<ids.length;k++){
	            					set.add(ids[k]);
	            				}
	            				srList.get(i).setStaffIds(set.toString().replace("[", "").replace("]", "").replace(" ", ""));
	            				srList.remove(j);
	            			}
	            		}
	                } 
				ArrayList<Site> siteList = new ArrayList<Site>();
				for(SiteRecord  sr :srList){
					Site site = sdi.getSiteById(sr.getSiteId(), con);
					if(site!=null)
						siteList.add(site);
				}
				JSONObject jo =new JSONObject();
				jo.put("site_list", siteList);
				jo.put("sr_list", srList);
				out.print(jo.toString());
			}
			else if(type.equals("month")){
				ArrayList<SiteRecord> srList = srdi.getSiteRecordByDate(3, date, con);
				String []week=srdi.dayForWeek(date);
				ArrayList<SiteRecord> srList1 = new ArrayList<SiteRecord>();
				 for (int i = 0; i < srList.size()-1; i++) {  
	            		for (int j = srList.size()-1; j > i; j--){
	            			
	            			if(srList.get(i).getSiteId()==srList.get(j).getSiteId()){
	            				srList.get(i).setNum(srList.get(i).getNum()+srList.get(j).getNum());
	            				HashSet<String> set = new HashSet<String>();
	            				String [] ids =srList.get(i).getStaffIds().split(",");
	            				for(int k=0;k<ids.length;k++){
	            					set.add(ids[k]);
	            				}
	            				 ids =srList.get(j).getStaffIds().split(",");
	            				for(int k=0;k<ids.length;k++){
	            					set.add(ids[k]);
	            				}
	            				srList.get(i).setStaffIds(set.toString().replace("[", "").replace("]", "").replace(" ", ""));
	            				srList.remove(j);
	            			}
	            		}
	                } 
				ArrayList<Site> siteList = new ArrayList<Site>();
				for(SiteRecord  sr :srList){
					Site site = sdi.getSiteById(sr.getSiteId(), con);
					if(site!=null)
						siteList.add(site);
				}
				JSONObject jo =new JSONObject();
				jo.put("site_list", siteList);
				jo.put("sr_list", srList);
				out.print(jo.toString());
			}
			else if(type.equals("1")){
				//System.out.println(request.getParameter("staffids"));
				String []staffids = request.getParameter("staffids").split(",");
				ArrayList<Staff> staffList = new ArrayList<Staff>();
				for(int i=0;i<staffids.length;i++){
					
					Staff staff = sdi1.getStaffByStaffId(Integer.parseInt(staffids[i]), con);
					if(staff!=null)
						staffList.add(staff);
				}
				JSONObject jo =new JSONObject();
				jo.put("staff_list", staffList);
				out.print(jo.toString());
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
