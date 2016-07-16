package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.swjtu.file.DBSave;
import edu.swjtu.util.DateUtil;

public class BackupDataServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		// typ1==1:export,==2:inport
		String type = request.getParameter("type");

		DBSave dbs = new DBSave();
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		String lastExTime = null;
		String lastIxTime = null;
		
		//获取浏览器访问访问服务器时传递过来的cookie数组
        Cookie[] cookies = request.getCookies();
        //如果用户是第一次访问，那么得到的cookies将是null
        if (cookies!=null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("lastExTime")) {
                	lastExTime = cookie.getValue();
                }else if(cookie.getName().equals("lastIxTime")){
                	lastIxTime = cookie.getValue();
                }
            }
        }
		if (null == type) {
			// 还没进入过页面
			session.setAttribute("lastExTime", lastExTime);
			session.setAttribute("lastIxTime", lastIxTime);
			request.getRequestDispatcher("../jsp_admin/backup_data.jsp")
					.forward(request, response);
		} else if (type.equals("1")) {
			try {
				dbs.backupDatebase("d://x.sql");
				String date = DateUtil.getDateTime();
				session.setAttribute("lastExTime", date);
				Cookie cookie = new Cookie("lastExTime",date);//创建一个cookie，cookie的名字是lastAccessTime
		        //将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
		        response.addCookie(cookie);
			} catch (InterruptedException e) {
				e.printStackTrace();
				pw.write("no");
			}
			pw.write("yes");
		} else if (type.equals("2")) {
			try {
				dbs.loadDataBase("d://x.sql");
				String date = DateUtil.getDateTime();
				session.setAttribute("lastIxTime", date);
				Cookie cookie = new Cookie("lastIxTime",date);//创建一个cookie，cookie的名字是lastAccessTime
		        response.addCookie(cookie);
				pw.write("yes");
			} catch (InterruptedException e) {
				e.printStackTrace();
				pw.write("no");
			}
		}
	}

}
