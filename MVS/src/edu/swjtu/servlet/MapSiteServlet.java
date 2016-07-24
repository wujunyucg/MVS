package edu.swjtu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.model.Site;
import edu.swjtu.util.DBUtil;

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
		SiteDaoImpl sdi = new  SiteDaoImpl();
		DBUtil db = new DBUtil();
		Connection con;
		try {
			con = db.getCon();
			ArrayList<Site> siteList = sdi.getAllSite(con);
			request.getSession().setAttribute("site_list", siteList);
			JSONObject jsonObject = new JSONObject();  
	        jsonObject.put("sitelist", siteList);  
	        System.out.print(jsonObject.toString());
	        request.getSession().setAttribute("json_site_list", jsonObject.toString());
			request.getRequestDispatcher("../jsp_user/map_site.jsp").forward(request,response);
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
