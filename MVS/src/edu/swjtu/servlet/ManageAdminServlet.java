package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.swjtu.impl.AdminDaoImpl;
import edu.swjtu.model.Admin;
import edu.swjtu.model.Powers;
import edu.swjtu.util.DBUtil;

public class ManageAdminServlet extends HttpServlet {
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DBUtil db = new DBUtil();
		
		Connection con = null;
		
		try {
			con = db.getCon();
			AdminDaoImpl adi = new AdminDaoImpl();
			ArrayList<Admin> adminList = adi.getAllAdmin(con);
			Map<String,String> map = new HashMap<String, String>();
			
			for(Admin adm : adminList){
				String p = Powers.getPowerType(adm.getPowerId());
				map.put(adm.getName(),p);
			}
			
			request.getSession().setAttribute("admin_map", map);
			response.sendRedirect("../jsp_admin/manage_admin.jsp");
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
