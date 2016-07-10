package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.swjtu.impl.AdminDaoImpl;
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.Admin;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;

public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateUserServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		User user = new User();
		user.setUserId(Integer.valueOf(request.getParameter("userid")).intValue());  
		//System.out.println(user.getUserId());
		String adminname=request.getParameter("admin");
		AdminDaoImpl adi = new AdminDaoImpl();
		DBUtil db = new DBUtil();
		Connection con;
		try {
			con = db.getCon();
			Admin admin = adi.getAdminByName(adminname, con);
			user.setAdminId(admin.getAdminId());
			user.setNumber(request.getParameter("number"));
			user.setPassword(request.getParameter("password"));
			user.setType(Integer.valueOf(request.getParameter("type")).intValue());
			UserDaoImpl udi = new UserDaoImpl();
			udi.updateUser(user, con);
			ArrayList<User> userList =new ArrayList<User>();
			userList=(ArrayList<User>) request.getSession().getAttribute("user_list");
			int status = Integer.valueOf(request.getParameter("staus")).intValue();
			userList.set(status,user);
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
