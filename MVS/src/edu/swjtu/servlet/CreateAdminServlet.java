package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.swjtu.impl.AdminDaoImpl;
import edu.swjtu.model.Admin;
import edu.swjtu.util.DBUtil;

public class CreateAdminServlet extends HttpServlet {
	
	DBUtil db = new DBUtil();
	AdminDaoImpl adminDao = new AdminDaoImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//type==1验证名字重名，2进行添加角色
		String type = request.getParameter("type");
		Connection con = null;
		PrintWriter pw = response.getWriter();
		String adminname = request.getParameter("adminName");
		
		if(type.equals("1")){
			Admin admin = null;
			
			try {
				con = db.getCon();
				admin = adminDao.getAdminByName(adminname, con);
				if(null==admin){
					pw.write("yes");
				}else{
					pw.write(admin.getName());
				}
			} catch (ClassNotFoundException | SQLException e) {
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
		}else if(type.equals("2")){
			String powerIds = request.getParameter("powerId");
			
			Admin admin = new Admin();
			admin.setName(adminname);
			admin.setPowerId(powerIds);
			
			try {
				con = db.getCon();
				int rs = adminDao.addAdmin(admin, con);
				if(rs>0){
					pw.write("yes");
				}else{
					pw.write("no");
				}
			} catch (ClassNotFoundException | SQLException e) {
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
	}
}
