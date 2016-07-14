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
 * UpdateStaffServlet.java类
 * 2016年7月14日
 * @author wujunyu
 * TODo
 */
public class UpdateStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UpdateStaffServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Staff staff = new Staff();
		staff.setStaffId(Integer.valueOf(request.getParameter("staffid")).intValue());  
		//System.out.println(user.getUserId());
		DBUtil db = new DBUtil();
		Connection con;
		try {
			con = db.getCon();
			staff.setAddress(request.getParameter("address"));
			staff.setArrangeId(Integer.valueOf(request.getParameter("arrange")).intValue());
			staff.setDepartment(request.getParameter("department"));
			staff.setGroup(request.getParameter("group"));
			staff.setLineId(Integer.valueOf(request.getParameter("line")).intValue());
			staff.setName(request.getParameter("name"));
			staff.setNumber(request.getParameter("number"));
			staff.setSiteId(Integer.valueOf(request.getParameter("site")).intValue());
			StaffDaoImpl sdi = new StaffDaoImpl();
			sdi.updateStaff(staff, con);
			db.closeCon(con);
			out.print(1);
			out.close();
			
		} catch (ClassNotFoundException e) {
			out.print(0);
			out.close();
			e.printStackTrace();
		} catch (SQLException e) {
			out.print(0);
			out.close();
			e.printStackTrace();
		}
		
	}

}
