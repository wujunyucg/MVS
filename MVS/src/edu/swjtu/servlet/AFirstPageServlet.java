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

import edu.swjtu.impl.AdminDaoImpl;
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.util.DBUtil;

public class AFirstPageServlet extends HttpServlet {
	DBUtil db = new DBUtil();
	
	public AFirstPageServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
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
			int adminNum = 0;
			adminNum = new AdminDaoImpl().getTotal(con);
			int userNum = 0; 
			userNum = new UserDaoImpl().getTotalNumber(con);
			
			ArrayList<Integer> totalNumber_a = new ArrayList<Integer> (); 
			totalNumber_a.add(adminNum);
			totalNumber_a.add(userNum);
			
			int allnum = 0;
			for(int i=0;i<2;i++){
				allnum += totalNumber_a.get(i);
			}
			ArrayList<String> totalRate_a = new ArrayList<String> ();
			double temp;
			for(int i=0;i<2;i++){
				temp = (totalNumber_a.get(i)+0.0)/(allnum+0.0)*1.0*100.0;
				totalRate_a.add(temp+"%");
			}
			int backPicture_a = 2;
			if((int)(1+Math.random()*(10-1+1)) <= 3){
				backPicture_a = 1;
			}
			
			request.getSession().setAttribute("totalNumber_a",totalNumber_a);
			request.getSession().setAttribute("totalRate_a",totalRate_a);
			request.getSession().setAttribute("backPicture_a",backPicture_a);
			request.getRequestDispatcher("../jsp_admin/firstPage_a.jsp").forward(request, response);		
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
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

	public void init() throws ServletException {
	}

}
