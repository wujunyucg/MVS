package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;
import edu.swjtu.util.DateUtil;

public class UserInfoServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DBUtil db = new DBUtil();
		Connection con = null;
		String type = request.getParameter("type");
		
		try {
			con = db.getCon();
			UserDaoImpl udi = new UserDaoImpl();
			PrintWriter pw = response.getWriter();
			
			User u = (User) request.getSession().getAttribute("user");
			if(type.equals("1")){
				String name = request.getParameter("name");
				
				User uu = udi.getUserByNumber(name, con);
				if(null!=uu){
					System.out.println("u:"+u.getUserId()+" uu:"+uu.getUserId());
				}
				if(null!=uu&&null!=u&&uu.getUserId()!=u.getUserId()){
					pw.write("same");
				}else if(null!=u){
					u.setNumber(name);
					int re = udi.updateUser(u, con);
					if(0<re){
						pw.write("yes");
					}else{
						pw.write("no");
					}
				}
			}else if(type.equals("2")){
				String pass = request.getParameter("oldPass");
				System.out.println("pass:"+pass+" upass:"+u.getPassword());
				if(!pass.equals(u.getPassword())){
					pw.write("notpass");
				}else{
					String newPass = request.getParameter("newPass");
					u.setPassword(newPass);
					int re = udi.updateUser(u, con);
					if(0<re){
						pw.write("yes");
					}else{
						pw.write("no");
					}
				}
			}
			pw.close();
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

}
