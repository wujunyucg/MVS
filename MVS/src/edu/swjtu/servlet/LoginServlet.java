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

import edu.swjtu.dao.UserDao;
import edu.swjtu.impl.AdminDaoImpl;
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.Admin;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;
import edu.swjtu.util.DateUtil;

public class LoginServlet extends HttpServlet {

	DBUtil db = new DBUtil();
	UserDaoImpl udi = new UserDaoImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		/*type=1:超级管理员2：普通管理员3:退出*/
		int type = Integer.parseInt(request.getParameter("type"));
		PrintWriter pw = response.getWriter();

		if(3==type){
			request.getSession().removeAttribute("user");
			pw.write("yes");
			return;
		}
		
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String validCode = request.getParameter("valid");
		
		Connection con = null;
		HttpSession session = request.getSession();
		
		//System.out.println(session.getAttribute("validationCode"));
		/*首先判断验证码*/
		if(!session.getAttribute("validationCode").equals(validCode)){
			pw.write("valid");
			return ;
		}

		try {
			con = db.getCon();
			User user = new User();
			user.setNumber(name);
			user.setPassword(password);
			user.setType(type);

			User result = udi.login(user, con);
			if (null != result) {
				session.setAttribute("user", result);
				String lastLoginTime = "从未登录";
				Cookie[] cookies = request.getCookies();
				if (cookies == null) {
					response.addCookie(new Cookie("lastLoginTime", DateUtil
							.getDateTime()));
				} else {
					for (int i = 0; i < cookies.length; i++) {
						Cookie cookie = cookies[i];
						if (cookie.getName().equals("lastLoginTime")) {
							lastLoginTime = cookie.getValue();
							cookie.setValue(DateUtil.getDateTime());
							break;
						}
					}
				}
				/**/
				session.setAttribute("lastLoginTime", lastLoginTime);
				
				if(1==type){
					pw.write("admin");
				}else if (2==type){
					AdminDaoImpl adi = new AdminDaoImpl();
					Admin admin = adi.getAdminById(result.getAdminId(), con);
					
					if(null!=admin){
						for(int i=0;i<11;i++){
							if(session.getAttribute("admin"+i)!=null){
								session.removeAttribute("admin"+i);
								System.out.println("remove admin"+i);
							}
						}
						String[]powers = admin.getPowerId().split(",");
						int len = powers.length;
						/*把权限分别放在session里*/
						for(int i=0;i<len;i++){
							session.setAttribute("admin"+powers[i], powers[i]);
						}
						pw.write("user");
					}else{
						pw.write("noadmin");
					}
				}
				// System.out.println("yes");
			} else {
				pw.write("no");
			}
			pw.flush();
			pw.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != con) {
				try {
					db.closeCon(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
