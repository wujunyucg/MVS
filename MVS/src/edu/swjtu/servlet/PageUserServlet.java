package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import edu.swjtu.model.User;
/**
 * 
 * PageUserServlet.java类
 * 2016年7月11日
 * @author wujunyu
 * TODo
 */
public class PageUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int ArrayList = 0;
       
    
    public PageUserServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		int page = Integer.valueOf(request.getParameter("page")).intValue();
		
		int pageNum =  (int) request.getSession().getAttribute("user_page_num");
		
		ArrayList<User> userList = (java.util.ArrayList<User>) request.getSession().getAttribute("user_list");
		
		 int pageAll = (int) Math.ceil((double)userList.size()/(double)pageNum);
		 request.getSession().setAttribute("user_page",page);
		 request.getSession().setAttribute("user_page_all",pageAll);
		 ArrayList<User> newUserList = new ArrayList<User>();
		 int start = (page - 1) * pageNum;
		 int end = page * pageNum - 1;
		 for(int i = start ;i<userList.size() && i<=end  ;i++){
			 newUserList.add(userList.get(i));
		 }
		 JSONObject jsonObject = new JSONObject();  
	        jsonObject.put("user", newUserList);  
	        jsonObject.put("user_all", pageAll);  
	        out.print(jsonObject.toString());
			out.close();
			
		 
	}

}
