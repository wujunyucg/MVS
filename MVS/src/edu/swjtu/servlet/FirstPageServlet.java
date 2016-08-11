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

import edu.swjtu.impl.ArrangeDaoImpl;
import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.util.DBUtil;

public class FirstPageServlet extends HttpServlet {
	DBUtil db = new DBUtil();
	
	public FirstPageServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	@SuppressWarnings("null")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");  
		Connection con = null;

		try {
			con = db.getCon();
			int staffNum = 0;
			staffNum = new StaffDaoImpl().getTotalNumber(con);
			int carNum = 0; 
			carNum = new CarDaoImpl().getTotal(con);
			int siteNum = 0; 
			siteNum = new SiteDaoImpl().getTotalNumber(con);
			int lineNum = 0;
			lineNum= new LineDaoImpl().getTotalNumber(con);
			int arrangeNum = 0;
			arrangeNum = new ArrangeDaoImpl().getTotalNumber(con);
			ArrayList<Integer> totalNumber = new ArrayList<Integer> (); 
			totalNumber.add(staffNum);
			totalNumber.add(carNum);
			totalNumber.add(siteNum);
			totalNumber.add(lineNum);
			totalNumber.add(arrangeNum);
			
			int allnum = 0;
			for(int i=0;i<5;i++){
				allnum += totalNumber.get(i);
			}
			ArrayList<String> totalRate = new ArrayList<String> ();
			double temp;
			for(int i=0;i<5;i++){
				temp = (totalNumber.get(i)+0.0)/(allnum+0.0)*1.0*100.0;
				totalRate.add(temp+"%");
			}
			int backPicture = 2;
			if((int)(1+Math.random()*(10-1+1)) <= 3){
				backPicture = 1;
			}
			
			request.getSession().setAttribute("totalNumber",totalNumber);
			request.getSession().setAttribute("totalRate",totalRate);
			request.getSession().setAttribute("backPicture",backPicture);
			request.getRequestDispatcher("../jsp_user/firstPage.jsp").forward(request, response);		
			
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
