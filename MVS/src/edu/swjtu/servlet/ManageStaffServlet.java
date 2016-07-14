package edu.swjtu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * ManageStaffServlet.java类
 * 2016年7月14日
 * @author wujunyu
 * TODo
 */
public class ManageStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ManageStaffServlet() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
