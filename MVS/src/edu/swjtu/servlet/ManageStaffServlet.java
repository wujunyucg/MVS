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
		
		if(request.getParameter("staff_page") == null){
			staffPage = 1;
		}
		else{
			staffPage = Integer.valueOf( (String) request.getParameter("staff_page")).intValue();
		}
		DBUtil db = new DBUtil();
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
			//System.out.println(staffList.get(0).getAddress());
			db.closeCon(con);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		response.sendRedirect("../jsp_user/maintenance_staff.jsp");
	}

}
