package edu.swjtu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.impl.SynchDaoImpl;
import edu.swjtu.model.Staff;
import edu.swjtu.model.Synch;
import edu.swjtu.util.DBUtil;

public class ManageSynchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ManageSynchServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int synch_page;
		DBUtil db = new DBUtil();
				if(request.getParameter("synch_page") == null){
					synch_page = 1;
				}
				else{
					synch_page = Integer.valueOf( (String) request.getParameter("synch_page")).intValue();
				}
				try {
					Connection con = db.getCon();
					SynchDaoImpl sdi = new SynchDaoImpl();
					int allNum = sdi.getSynchNum(con);
					int synchNum = 2;
					ArrayList<Synch> synchList = sdi.getSynch(synch_page, synchNum, con);
					int pageAll = (int) Math.ceil((double)allNum/(double)synchNum);
					request.getSession().setAttribute("synch_begin_page",( Math.ceil((double)synch_page/5.0)-1)*5+1);
					request.getSession().setAttribute("synch_page_num", synchNum);
					request.getSession().setAttribute("synch_page_all", pageAll);
					request.getSession().setAttribute("synch_page", synch_page);
					request.getSession().setAttribute("synch_list", synchList);
					//System.out.println(staffList.get(0).getAddress());
					db.closeCon(con);
					response.sendRedirect("../jsp_user/synch_staff.jsp");
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
		
	}

}
