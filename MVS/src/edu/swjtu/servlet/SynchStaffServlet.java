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

import org.hamcrest.core.IsNull;

import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;

/**
 * 
 * SynchStaffServlet.java类
 * 2016年7月15日
 * @author wujunyu
 * TODo
 */
public class SynchStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SynchStaffServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBUtil db = new DBUtil();
		PrintWriter out = response.getWriter();
		if(request.getParameter("type").equals("0")){
			try {
					Connection con = db.getCon();
					StaffDaoImpl sdi = new StaffDaoImpl();
					String number = request.getParameter("number");
					Staff staff = sdi.getStaffByNumber(number, con);
					if(null != staff)
						out.print(1);
					else
						out.print(2);
					//System.out.println(staff.getNumber());
					db.closeCon(con);
					out.close();
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
		}
		else if(request.getParameter("type").equals("1")){
			try {
				Connection con = db.getCon();
				StaffDaoImpl sdi = new StaffDaoImpl();
				String number = request.getParameter("staff_number");
				String name = request.getParameter("staff_name");
				String department = request.getParameter("staff_department");
				String group = request.getParameter("staff_group");
				String address = request.getParameter("staff_address");
				Staff staff = sdi.getStaffByNumber(number, con);
				Staff staff1 = new Staff();
				staff1.setNumber(number);
				staff1.setName(name);
				staff1.setDepartment(department);
				staff1.setGroup(group);;
				staff1.setAddress(address);
				sdi.addOneStaff(staff1, con);
				out.print(1);
				//System.out.println(staff.getNumber());
				db.closeCon(con);
				out.close();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
	}
		
	}

}
