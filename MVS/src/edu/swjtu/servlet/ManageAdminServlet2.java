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
import edu.swjtu.model.Admin;
import edu.swjtu.model.Powers;
import edu.swjtu.util.DBUtil;

public class ManageAdminServlet2 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		//type==1代表查看详情，2代表返回角色的权限,3代表更新角色,4代表删除角色
		String type = request.getParameter("type");
		String adminname = request.getParameter("adminName");
		
		DBUtil db = new DBUtil();
		Connection con = null;
		AdminDaoImpl adi = new AdminDaoImpl();
		PrintWriter pw = response.getWriter();
		
		if(type.equals("1")){
			try {
				con = db.getCon();
				Admin admin = adi.getAdminByName(adminname, con);
				if(null!=admin){
					String re = "<b>";
					ArrayList<String> list = Powers.getPower(admin.getPowerId());
					for(int i=0;i<list.size();i++){
						re+=list.get(i)+"&nbsp;&nbsp;";
					}
					re+="</b>";
					pw.write(re);
				}else{
					pw.write("no");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
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
		}else if (type.equals("2")){
			try {
				con = db.getCon();
				Admin admin = adi.getAdminByName(adminname, con);
				if(null!=admin){
					pw.write(admin.getPowerId());
				}else{
					pw.write("no");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
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
		}else if(type.equals("3")){
			try {
				String ids = request.getParameter("powerId");
				String initName = request.getParameter("initName");
				con = db.getCon();
				Admin admin = adi.getAdminByName(initName, con);
				admin.setName(adminname);
				admin.setPowerId(ids);
				int re = adi.updateAdmin(admin, con);
				if(0<re){
					String ss = adminname+","+Powers.getPowerType(ids);
					pw.write(ss);
				}else{
					pw.write("no");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
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
		}else if(type.equals("4")){
			try {
				con = db.getCon();
				Admin admin = adi.getAdminByName(adminname, con);
				if(null!=admin){
					int id = admin.getAdminId();
					int user = new UserDaoImpl().deleteUserByAdminId(id, con);
					int re = adi.deleteAdmin(id, con);
					if(re>0){
						pw.write(user+"");
					}else{
						pw.write("no");
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
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
		pw.close();
	}
}
