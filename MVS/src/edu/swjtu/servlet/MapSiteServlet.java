package edu.swjtu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * MapSiteServlet.java类
 * 2016年7月24日
 * @author wujunyu
 * TODo
 */
public class MapSiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MapSiteServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("../jsp_user/map_site.jsp").forward(request,response);
	}

}
