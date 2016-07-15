package edu.swjtu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;


public class SearchStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SearchStaffServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.valueOf( (String) request.getParameter("type")).intValue();
		String str2 = request.getParameter("content");
		DBUtil db = new DBUtil();
		try {
			Connection con = db.getCon();
			int startPage = 1;
			StaffDaoImpl sdi = new StaffDaoImpl();
			int pageNum = (int) request.getSession().getAttribute("staff_page_num");
			ArrayList<Staff> staffList = new ArrayList<Staff>();
			
			int str;
			//System.out.println(type);
			int allNum = 0;
			switch(type)
			{
			case 1: staffList = sdi.getStaffByAnyStr("staff_number", str2, startPage, pageNum, con); allNum = 1;break;
			case 2:	staffList = sdi.getStaffByAnyStr("staff_name", str2, startPage, pageNum, con);allNum= sdi.getStaffByName(str2, con).size();break;
			case 3:	staffList = sdi.getStaffByAnyStr("staff_department", str2, startPage, pageNum, con);allNum= sdi.getStaffByDepartment(str2, con).size();break;
			case 4:	staffList = sdi.getStaffByAnyStr("staff_group", str2, startPage, pageNum, con);allNum= sdi.getStaffByGroup(str2, con).size();break;
			case 5:	 str = Integer.valueOf(str2).intValue(); staffList = sdi.getStaffByAnyInt("staff_arrangeId", str, startPage, pageNum, con);allNum= sdi.getStaffByArrangeId(str, con).size();break;
			case 6:  str = Integer.valueOf(str2).intValue(); staffList = sdi.getStaffByAnyInt("staff_lineId", str, startPage, pageNum, con);allNum= sdi.getStaffByLineId(str, con).size();break;
			case 7:  str = Integer.valueOf(str2).intValue(); staffList = sdi.getStaffByAnyInt("staff_siteId", str, startPage, pageNum, con);allNum= sdi.getStaffBySiteId(str, con).size();break;
			}
			int pageAll = (int) Math.ceil((double)allNum/(double)pageNum);
			request.getSession().setAttribute("staff_begin_page",1);
			request.getSession().setAttribute("staff_page_num", pageNum);
			request.getSession().setAttribute("staff_page_all", pageAll);
			request.getSession().setAttribute("staff_page", startPage);
			request.getSession().setAttribute("staff_list", staffList);
			request.getSession().setAttribute("staff_type", type);
			request.getSession().setAttribute("staff_search_content", str2);
			db.closeCon(con);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		response.sendRedirect("../jsp_user/maintenance_staff.jsp");
	}

}
