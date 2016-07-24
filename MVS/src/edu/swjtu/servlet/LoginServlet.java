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
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;
import edu.swjtu.util.DateUtil;

public class LoginServlet extends HttpServlet {

	DBUtil db = new DBUtil();
	UserDaoImpl udi = new UserDaoImpl();
	
	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");  
		
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("in");
		Connection con = null;
		
		try {
			con = db.getCon();
			User user = new User();
			user.setNumber(name);
			user.setPassword(password);
			
			User result = udi.login(user, con);
			PrintWriter pw = response.getWriter();
			HttpSession session = request.getSession();
			if(null!=result){
				session.setAttribute("user", result);
				pw.write("yes");
				String lastLoginTime = "从未登录";
				Cookie[]cookies = request.getCookies();
				if(cookies==null){
					response.addCookie(new Cookie("lastLoginTime", DateUtil.getDateTime()));
				}else{
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
				session.setAttribute("power", "1,2,3,7");
				
				//System.out.println("yes");
			}else{
				pw.write("no");
				//System.out.println("no");				
			}
			pw.flush();
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

	public void init() throws ServletException {
	}
}
