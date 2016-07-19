package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.Car;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;

public class CreateCarServlet extends HttpServlet {
	DBUtil db = new DBUtil();
	UserDaoImpl udi = new UserDaoImpl();
	/**
	 * Constructor of the object.
	 */
	public CreateCarServlet() {
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
		
		
		if(type.equals("1")){
			Car car = new Car();
			String lic_plat = request.getParameter("lic_plat"); 
			String bran = request.getParameter("bran");
			String res_date = request.getParameter("res_date");
			String ins_date = request.getParameter("ins_date");
			String dri_lice = request.getParameter("dri_lice");
			String lice = request.getParameter("lice"); 
			String numb = request.getParameter("numb");
			String driv = request.getParameter("driv"); 
			if(!lic_plat.equals("") && !bran.equals("")&& !res_date.equals("")&& !ins_date.equals("")&& !dri_lice.equals("")
					&& !lice.equals("") && !numb.equals("")&& !driv.equals("")){
				car.setArrangeId("-1");
				car.setBrand(bran);
				car.setDriver(driv);
				car.setDrivingLicense(dri_lice);
				car.setInsuranceDate(ins_date);
				car.setLicense(lice);
				car.setLicensePlate(lic_plat);
				car.setNumber(Integer.parseInt(numb));
				car.setRegistrationDate(res_date);
				
				int rs = new CarDaoImpl().addOneCar(car, con);
				if(rs>0){
					pw.write("yes");
				}else{
					pw.write("no");
				}
			}
		}
		else if(type.equals("2")){
			Car car= null;
			String type_s = request.getParameter("type_s");
			try {
				if(type_s.equals("1")){
					car =  new CarDaoImpl().getCarByLicensePlate(request.getParameter("lic_plat"), con);
				}else if(type_s.equals("2")){
					car =  new CarDaoImpl().getCarByLicense(request.getParameter("lice"), con);
				}else{}
				if(null==car){
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
		}else if(type.equals("3")){	//判断座位数是整型数据
			String seat_num = null;
			seat_num = request.getParameter("numb");
			int jud = 1;
			try {  		
	            Integer.parseInt(seat_num);  	            
	        } catch (NumberFormatException e) {  
	        	jud = 0;
			}
			if(jud == 1){
				pw.write("yes");		
			}else{
				pw.write("no");
			}
			System.out.println(seat_num + "|" + jud);
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
