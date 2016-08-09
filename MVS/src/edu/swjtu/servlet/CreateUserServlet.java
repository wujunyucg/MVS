package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import edu.swjtu.dao.UserDao;
import edu.swjtu.impl.AdminDaoImpl;
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.Admin;
import edu.swjtu.model.User;
import edu.swjtu.model.Powers;
import edu.swjtu.util.DBUtil;

/**
 * 
 * 2016年7月9日下午5:10:37
 * @author mischief
 * TODO 创建用户servlet
 */
public class CreateUserServlet extends HttpServlet {
	
	DBUtil db = new DBUtil();
	UserDaoImpl udi = new UserDaoImpl();
	
	/**
	 * Constructor of the object.
	 */
	public CreateUserServlet() {
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
			e1.printStackTrace();
		}
		
		ArrayList<Admin> list = null;
		try {
			list = new AdminDaoImpl().getAllAdmin(con);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		

		for(int i=0;i<list.size();i++)
			list.get(i).setPowerId(new Powers().getPowerType_CU(list.get(i).getPowerId()));
		
		request.getSession().setAttribute("list", list);
        request.getRequestDispatcher("../jsp_admin/create_user.jsp").forward(request, response);
		
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
