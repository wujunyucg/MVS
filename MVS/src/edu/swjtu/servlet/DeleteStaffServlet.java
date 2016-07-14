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

import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;

/**
 * 
 * DeleteStaffServlet.java类
 * 2016年7月14日
 * @author wujunyu
 * TODo
 */
public class DeleteStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteStaffServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String onlyOne = request.getParameter("onlyOne");
		StaffDaoImpl sdi = new StaffDaoImpl();
		DBUtil db = new DBUtil();
		Connection con;
		
		int pageAll = (int) request.getSession().getAttribute("staff_page_all");
		int pageNum = (int) request.getSession().getAttribute("staff_page_num");
		try {
			con = db.getCon();
			int allNum = sdi.getStaffNum(con);
			Staff staff = sdi.getLastStaff(con);
			if(onlyOne.equals("1")){
				int staffId = Integer.valueOf(request.getParameter("staffId")).intValue();
				

				sdi.deleteOneStaff(staffId, con);
				if(allNum%pageNum == 1 && staffId == staff.getStaffId())
					out.print(2);
				else
					out.print(1);
				db.closeCon(con);
				out.close();
			}
			else if(onlyOne .equals("0")){
				 String staffIds[] = request.getParameter("ids").replace("[", "").replace("]", "").split(","); 
				// System.out.println(a[0]);
				 ArrayList<Staff> staffList = new ArrayList<Staff> ();
				 int lastId = 0;
				 for(String staffId : staffIds){
					 Staff staff1 = new Staff();
					 lastId = Integer.valueOf(staffId).intValue();
					 staff1.setStaffId(lastId);
					 staffList.add(staff1);
					 
				 }
				
				sdi.deleteListStaff(staffList, con);
				if((allNum%pageNum == staffList.size()|| allNum%pageNum == 0) && lastId == staff.getStaffId())
					out.print(2);
				else
					out.print(1);
				db.closeCon(con);
				out.close();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
