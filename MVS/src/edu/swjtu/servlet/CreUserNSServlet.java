package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;

public class CreUserNSServlet extends HttpServlet {
	DBUtil db = new DBUtil();
	UserDaoImpl udi = new UserDaoImpl();
	
	/**
	 * Constructor of the object.
	 */
	public CreUserNSServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");  
		Connection con = null;

		try {
			con = db.getCon();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String type = request.getParameter("type");
		PrintWriter pw = response.getWriter();
		
		System.out.println(type + "************");
		
		if(type.equals("2")){
			User user = new User();
			
			String user_number = request.getParameter("userNumber");
			String user_adminchoice = request.getParameter("adminId");
			String user_password = request.getParameter("userPassword");	
			System.out.println("user_number:" + user_number);
			System.out.println("user_adminchoice:" + user_adminchoice);
			System.out.println("user_password:" + user_password);
			
			if(!user_number.equals("") && !user_adminchoice.equals("")){
				user.setAdminId(Integer.parseInt(user_adminchoice));
				user.setNumber(user_number);
				user.setType(2);
				if(user_password.equals(""))
					user.setPassword("123456");
				else
					user.setPassword(user_password);
				
				try {
					int rs = new UserDaoImpl().addUser(user, con);
					if(rs>0){
						pw.write("yes");
					}else{
						pw.write("no");
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		else if(type.equals("1")){
			User user = null;
			try {
				user =  new UserDaoImpl().getUserByNumber(request.getParameter("userNumber"), con);
				if(null==user){
					pw.write("yes");
				}else{
					pw.write("no");
				}
			} catch (SQLException e) {
				pw.write("no");
				e.printStackTrace();
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

		
		try {
			db.closeCon(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
