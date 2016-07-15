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
import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.Admin;
import edu.swjtu.model.Car;
import edu.swjtu.model.Powers;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;

public class ManageCarServlet extends HttpServlet {
	DBUtil db = new DBUtil();
	UserDaoImpl udi = new UserDaoImpl();
	
	public ManageCarServlet() {
		super();
	}

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
		
		int pageSize = 10;
		int pageTotal=0;
		String page_index = request.getParameter("page_index");
		
		System.out.println(page_index);
		
		if(type.equals("1")){
			ArrayList<Car> list = null;
			CarDaoImpl carr = new CarDaoImpl();
			try {
				list = carr.getPageCar(con, Integer.valueOf(page_index).intValue(), pageSize);
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				pageTotal = carr.getTotal(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(pageTotal);
			
			if( pageTotal%pageSize ==0){
				request.getSession().setAttribute("allpage",(int) pageTotal/pageSize);
			}
			else {
				request.getSession().setAttribute("allpage",(int) pageTotal/pageSize+1);
			}
			
			request.getSession().setAttribute("pageSize",pageSize);
			request.getSession().setAttribute("page_index", Integer.valueOf(page_index).intValue());
			
			request.getSession().setAttribute("list", list);
	        request.getRequestDispatcher("../jsp_user/manage_car.jsp").forward(request, response);		
		}
		else if(type.equals("2")){
			Car car = null;	
			car =  new CarDaoImpl().getCarById(Integer.valueOf(request.getParameter("carId")).intValue(), con);
			car.setLicensePlate(request.getParameter("licensePlate"));
			car.setBrand(request.getParameter("brand"));
			car.setRegistrationDate(request.getParameter("registrationDate"));
			car.setInsuranceDate(request.getParameter("insuranceDate"));
			car.setDrivingLicense(request.getParameter("drivingLicense"));
			car.setLicense(request.getParameter("license"));
			car.setArrangeId(request.getParameter("arrangeId"));
			car.setDriver(request.getParameter("driver"));
			car.setNumber(Integer.valueOf(request.getParameter("number")).intValue());
			int u = new CarDaoImpl().updateCar(car, con);
			try {
				if(u!=0){
					pw.write("yes");
				}else{
					pw.write("no");
				}
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
		else if(type.equals("3")){
			Car car = null;	
			car =  new CarDaoImpl().getCarByLicensePlate(request.getParameter("licensePlate"), con);
			int u = new CarDaoImpl().deleteOneCar(car, con);
			try {
				if(u!=0){
					pw.write("yes");
				}else{
					pw.write("no");
				}
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
		else{
			
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
