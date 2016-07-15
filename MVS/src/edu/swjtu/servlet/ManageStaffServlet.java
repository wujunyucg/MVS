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

/**
 * 
 * ManageStaffServlet.java类
 * 2016年7月14日
 * @author wujunyu
 * TODo
 */
public class ManageStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ManageStaffServlet() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int staffPage;
		DBUtil db = new DBUtil();
		if(request.getParameter("staff_type") == null){
				if(request.getParameter("staff_page") == null){
					staffPage = 1;
				}
				else{
					staffPage = Integer.valueOf( (String) request.getParameter("staff_page")).intValue();
				}
				try {
					Connection con = db.getCon();
					StaffDaoImpl sdi = new StaffDaoImpl();
					int allNum = sdi.getStaffNum(con);
					int staffNum = 2;
					ArrayList<Staff> staffList = sdi.getStaffByPage(staffPage, staffNum, con);
					int pageAll = (int) Math.ceil((double)allNum/(double)staffNum);
					request.getSession().setAttribute("staff_begin_page",( Math.ceil((double)staffPage/5.0)-1)*5+1);
					request.getSession().setAttribute("staff_page_num", staffNum);
					request.getSession().setAttribute("staff_page_all", pageAll);
					request.getSession().setAttribute("staff_page", staffPage);
					request.getSession().setAttribute("staff_list", staffList);
					request.getSession().removeAttribute("staff_type");
					request.getSession().removeAttribute("staff_search_contnet");
					//System.out.println(staffList.get(0).getAddress());
					db.closeCon(con);
					response.sendRedirect("../jsp_user/maintenance_staff.jsp");
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
		}
		else{
			if(request.getParameter("staff_page") == null){
				staffPage = 1;
			}
			else{
				staffPage = Integer.valueOf( (String) request.getParameter("staff_page")).intValue();
			}
			try {
				Connection con = db.getCon();
				StaffDaoImpl sdi = new StaffDaoImpl();
				int type = Integer.valueOf( (String) request.getParameter("staff_type")).intValue();
				String str2 = (String) request.getSession().getAttribute("staff_search_content");
				ArrayList<Staff> staffList = new ArrayList<Staff>();
				int allNum = 0;
				int pageNum = (int) request.getSession().getAttribute("staff_page_num");
				int str;
				
				switch(type)
				{
				case 1: staffList = sdi.getStaffByAnyStr("staff_number", str2, staffPage, pageNum, con); allNum = 1;break;
				case 2:	staffList = sdi.getStaffByAnyStr("staff_name", str2, staffPage, pageNum, con);allNum= sdi.getStaffByName(str2, con).size();break;
				case 3:	staffList = sdi.getStaffByAnyStr("staff_department", str2, staffPage, pageNum, con);allNum= sdi.getStaffByDepartment(str2, con).size();break;
				case 4:	staffList = sdi.getStaffByAnyStr("staff_group", str2, staffPage, pageNum, con);allNum= sdi.getStaffByGroup(str2, con).size();break;
				case 5:	 str = Integer.valueOf(str2).intValue(); staffList = sdi.getStaffByAnyInt("staff_arrangeId", str, staffPage, pageNum, con);allNum= sdi.getStaffByArrangeId(str, con).size();break;
				case 6:  str = Integer.valueOf(str2).intValue(); staffList = sdi.getStaffByAnyInt("staff_lineId", str, staffPage, pageNum, con);allNum= sdi.getStaffByLineId(str, con).size();break;
				case 7:  str = Integer.valueOf(str2).intValue(); staffList = sdi.getStaffByAnyInt("staff_siteId", str, staffPage, pageNum, con);allNum= sdi.getStaffBySiteId(str, con).size();break;
				}
				int pageAll = (int) Math.ceil((double)allNum/(double)pageNum);
				request.getSession().setAttribute("staff_begin_page",( Math.ceil((double)staffPage/5.0)-1)*5+1);
				request.getSession().setAttribute("staff_page_num", pageNum);
				request.getSession().setAttribute("staff_page_all", pageAll);
				request.getSession().setAttribute("staff_page", staffPage);
				request.getSession().setAttribute("staff_list", staffList);
				//System.out.println(staffList.get(0).getAddress());
				db.closeCon(con);
				response.sendRedirect("../jsp_user/maintenance_staff.jsp");
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
	}

}
