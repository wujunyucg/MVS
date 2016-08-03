package edu.swjtu.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Car;
import edu.swjtu.model.Site;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;

public class APPLoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		
		DBUtil db = new DBUtil();
		Connection con = null;
		PrintWriter pw = response.getWriter();
		
		if(null==type||name==null){
			pw.write("no");
			return;
		}
		try {
			con = db.getCon();
			//员工
			if(type.equals("1")){
				
				StaffDaoImpl sdi = new StaffDaoImpl();
				
				Staff sf = sdi.getStaffByNumber(name, con);
				if(null!=sf){
					SiteDaoImpl si = new SiteDaoImpl();
					Site s = si.getSiteById(sf.getSiteId(), con);
					String address;
					if(null!=s){
						address = s.getAddress();
					}else{
						address = "未知站点";
					}
					pw.write(sf.getName()+","+sf.getDepartment()+","+address);
				}else{
					pw.write("no");
				}
			}else{
				CarDaoImpl cdi = new CarDaoImpl();
				Car car = cdi.getCarByDriver(name, con);
				if(null!=car){
					pw.write("yes");
				}else{
					pw.write("no");
				}
			}
			pw.close();
		} catch (ClassNotFoundException | SQLException e) {
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

}
