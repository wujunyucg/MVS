package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.internet.NewsAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.util.NewBeanInstanceStrategy;

import org.hamcrest.core.IsNull;

import com.sun.accessibility.internal.resources.accessibility;

import edu.swjtu.excel.StaffExcel;
import edu.swjtu.file.FileUpload;
import edu.swjtu.impl.BuffStaffDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.BuffStaff;
import edu.swjtu.model.Staff;
import edu.swjtu.quartz.ChangeTriger;
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

	
	@SuppressWarnings({ "static-access", "deprecation" })
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
		else if(request.getParameter("type").equals("2")){
			
				FileUpload fu = new FileUpload();
				int ok = fu.upload(request);
				System.out.println(fu.getSignalPath());
				if(2 == ok && ((fu.getAllPath().substring(fu.getAllPath().lastIndexOf(".")+1).equals("xls")) || (fu.getAllPath().substring(fu.getAllPath().lastIndexOf(".")+1).equals("xlsx")))){
					out.print(1);
				}
				else if(0 == ok)
				{
					out.print(2);
				}
					
				else if(1 == ok){
					StaffExcel se = new StaffExcel();
					ArrayList<Staff> staffList = new ArrayList<Staff>();
					try {
						Connection con = db.getCon();
						staffList = se.inport(fu.getAllPath());
						if(staffList == null){
							out.print(6);
						}			
						else if(staffList.size() == 0){
							out.print(3);
						}
							
						else{
							int flag = 0;
							StaffDaoImpl sdi = new StaffDaoImpl();
							for(Staff staff : staffList){
								if(sdi.getStaffByNumber(staff.getNumber(), con) !=null){
									flag = 1;
									break;
								}	
							}
							if(flag == 1){
								out.print(4);
							}
								
							else{
								sdi.addListStaff(staffList, con);
								out.print(5);
							}
							db.closeCon(con);
						}
							
					} catch (InstantiationException e) {
						
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						
						e.printStackTrace();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				}
				out.close();
				fu.deleteFile("/"+fu.getSignalPath());
		}
		
		else if(request.getParameter("type").equals("3")){		
			try {
				Connection con = db.getCon();
				BuffStaffDaoImpl bsdi = new BuffStaffDaoImpl();
				String number = request.getParameter("staff_number");
				String name = request.getParameter("staff_name");
				String department = request.getParameter("staff_department");
				String group = request.getParameter("staff_group");
				String address = request.getParameter("staff_address");
				BuffStaff bstaff = new BuffStaff();
				bstaff.setNumber(number);
				bstaff.setName(name);
				bstaff.setDepartment(department);
				bstaff.setGroup(group);;
				bstaff.setAddress(address);
				bsdi.addOneBuffStaff(bstaff, con);
				out.print(1);
				//System.out.println(bstaff.getNumber());
				db.closeCon(con);
				out.close();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		else if(request.getParameter("type").equals("4")){
			
			FileUpload fu = new FileUpload();
			int ok = fu.upload(request);
			System.out.println(fu.getSignalPath());
			if(2 == ok && ((fu.getAllPath().substring(fu.getAllPath().lastIndexOf(".")+1).equals("xls")) || (fu.getAllPath().substring(fu.getAllPath().lastIndexOf(".")+1).equals("xlsx")))){
				out.print(1);
			}
			else if(0 == ok)
			{
				out.print(2);
			}	
			else if(1 == ok){
				StaffExcel se = new StaffExcel();
				ArrayList<Staff> staffList = new ArrayList<Staff>();
				try {
					Connection con = db.getCon();
					staffList = se.inport(fu.getAllPath());
					if(staffList == null){
						out.print(6);
					}			
					else if(staffList.size() == 0){
						out.print(3);
					}
						
					else{
						int flag = 0;
						StaffDaoImpl sdi = new StaffDaoImpl();
						for(Staff staff : staffList){
							if(sdi.getStaffByNumber(staff.getNumber(), con) !=null){
								flag = 1;
								break;
							}	
						}
						if(flag == 1){
							out.print(4);
						}
							
						else{
							BuffStaffDaoImpl bsdi = new BuffStaffDaoImpl();
							ArrayList<BuffStaff> bstaffList = new ArrayList<BuffStaff>();
							for(Staff staff : staffList){
								BuffStaff bstaff = new BuffStaff();
								bstaff.setNumber(staff.getNumber());
								bstaff.setName(staff.getName());
								bstaff.setDepartment(staff.getDepartment());
								bstaff.setGroup(staff.getGroup());;
								bstaff.setAddress(staff.getAddress());
								bstaffList.add(bstaff);
							}
							bsdi.addListBuffStaff(bstaffList, con);
							out.print(5);
						}
						db.closeCon(con);
					}
						
				} catch (InstantiationException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			out.close();
			fu.deleteFile("/"+fu.getSignalPath());
		}
		else if(request.getParameter("type").equals("5")){		
			
				String time = request.getParameter("time");
				ChangeTriger ct = new ChangeTriger();
				ct.changeTime(time,  request.getRealPath("/"));
				out.print(1);
				//System.out.println( request.getSession().getServletContext().getRealPath("/"));
				out.close();
		
		}
	}

}
