package edu.swjtu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.swjtu.impl.AdminDaoImpl;
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.Admin;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;


public class ManageUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ManageUserServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DBUtil db = new DBUtil();
		try {
			Connection con = db.getCon();
			UserDaoImpl usi = new UserDaoImpl();
			ArrayList<User> userList = usi.getAllUser(con);
			AdminDaoImpl adi = new AdminDaoImpl();
			ArrayList<Admin> adminList = adi.getAllAdmin(con);
			int userPage = 1;
			int pageNum = 5;
			int pageAll = (int) Math.ceil((double)userList.size()/(double)pageNum);
			request.getSession().setAttribute("user_page_num", pageNum);
			request.getSession().setAttribute("user_page_all", pageAll);
			request.getSession().setAttribute("user_page", userPage);
			request.getSession().setAttribute("user_list", userList);
			request.getSession().setAttribute("user_admin_list", adminList);
			db.closeCon(con);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		response.sendRedirect("../jsp_admin/manage_user.jsp");
	}

}
