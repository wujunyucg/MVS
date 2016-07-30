package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Line;
import edu.swjtu.model.Site;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;

/**
 * 
 * UpdateStaffServlet.java类
 * 2016年7月14日
 * @author wujunyu
 * TODo
 */
public class UpdateStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UpdateStaffServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Staff staff = new Staff();
		staff.setStaffId(Integer.valueOf(request.getParameter("staffid")).intValue());  
		//System.out.println(user.getUserId());
		DBUtil db = new DBUtil();
		StaffDaoImpl sdi = new StaffDaoImpl();
		Connection con;
		try {
			con = db.getCon();
			Staff staff1 = sdi.getStaffByStaffId(Integer.valueOf(request.getParameter("staffid")).intValue(), con);
			SiteDaoImpl sdi1 = new SiteDaoImpl();
			LineDaoImpl ldi = new LineDaoImpl();
			Line line = new Line();
			Site site = new Site();
			int lineId=staff1.getLineId();
			if(lineId==-1){
				if(Integer.valueOf(request.getParameter("line")).intValue()==-1){
					staff.setLineId(Integer.valueOf(request.getParameter("line")).intValue());
				}
				else{
					staff.setLineId(Integer.valueOf(request.getParameter("line")).intValue());
					line = ldi.getLineById(con,Integer.valueOf(request.getParameter("line")).intValue());
					line.setNum(line.getNum()+1);
					ldi.updateLine(con, line);
				}
			}
			else{
				line = ldi.getLineById(con, lineId);
				line.setNum(line.getNum()-1);
				ldi.updateLine(con, line);
				line = ldi.getLineById(con,Integer.valueOf(request.getParameter("line")).intValue());
				line.setNum(line.getNum()+1);
				ldi.updateLine(con, line);
				staff.setLineId(Integer.valueOf(request.getParameter("line")).intValue());
			}
			int siteId = staff1.getSiteId();
			int siteId1 = Integer.valueOf(request.getParameter("site")).intValue();
			if(siteId==-1){
				if(siteId1==-1){
					staff.setSiteId(-1);
				}
				else{
					staff.setSiteId(siteId1);
					site = sdi1.getSiteById(siteId1, con);
					site.setPeoNum(site.getPeoNum()+1);
					sdi1.updateSite(site, con);
				}
			}
			else{
				site = sdi1.getSiteById(siteId, con);
				site.setPeoNum(site.getPeoNum()-1);
				sdi1.updateSite(site, con);
				site = sdi1.getSiteById(siteId1, con);
				site.setPeoNum(site.getPeoNum()+1);
				sdi1.updateSite(site, con);
				staff.setSiteId(siteId1);
				
			}
			
			line = ldi.getLineById(con, staff1.getLineId());
			staff.setAddress(request.getParameter("address"));
			staff.setArrangeId(Integer.valueOf(request.getParameter("arrange")).intValue());
			staff.setDepartment(request.getParameter("department"));
			staff.setGroup(request.getParameter("group"));
			
			staff.setName(request.getParameter("name"));
			staff.setNumber(request.getParameter("number"));
			
			
			sdi.updateStaff(staff, con);
			db.closeCon(con);
			out.print(1);
			out.close();
			
		} catch (ClassNotFoundException e) {
			out.print(0);
			out.close();
			e.printStackTrace();
		} catch (SQLException e) {
			out.print(0);
			out.close();
			e.printStackTrace();
		}
		
	}

}
