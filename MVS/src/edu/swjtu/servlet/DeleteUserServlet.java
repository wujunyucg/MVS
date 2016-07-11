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

import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;

/**
 * 
 * DeleteUserServlet.java类
 * 2016年7月11日
 * @author wujunyu
 * TODo
 */
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteUserServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String onlyOne = request.getParameter("onlyOne");
		UserDaoImpl udi = new UserDaoImpl();
		DBUtil db = new DBUtil();
		Connection con;
		try {
			con = db.getCon();
			if(onlyOne != null){
				int userId = Integer.valueOf(request.getParameter("userId")).intValue();
				int status = Integer.valueOf(request.getParameter("status")).intValue();
				udi.deleteUser(userId, con);
				ArrayList<User> userList =new ArrayList<User>();
				userList=(ArrayList<User>) request.getSession().getAttribute("user_list");
				userList.remove(status);
				db.closeCon(con);
				out.print(1);
				out.close();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
