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
import javax.servlet.http.HttpSession;

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
		
		int startPage = 1;
		int pageNum = 10;
		
		String sp = request.getParameter("adminStartPage");
		
		if(null!=sp){
			startPage = Integer.parseInt(sp);
		}
		int total = 0;
		
		try {
			HttpSession session = request.getSession();
			con = db.getCon();
			AdminDaoImpl adi = new AdminDaoImpl();
			ArrayList<Admin> adminList = adi.getPageAdmin(con, startPage, pageNum);
			
			total = adi.getTotal(con);
			int total_page = 1;
			if(0<total){
				total_page = (total-1)/pageNum+1;//总共的页数
			}
			Map<String,String> map = new HashMap<String, String>();
			
			for(Admin adm : adminList){
				String p = Powers.getPowerType(adm.getPowerId());
				map.put(adm.getName(),p);
			}
//			System.out.println(adminList.get(0).getName());
			session.setAttribute("admin_map", map);
			session.setAttribute("admin_total", total_page);
			session.setAttribute("adminStartPage", startPage);
			request.getRequestDispatcher("../jsp_admin/manage_admin.jsp").forward(request, response);
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
