package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.internet.NewsAddress;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.NewBeanInstanceStrategy;

import org.hamcrest.core.IsNull;

import com.sun.accessibility.internal.resources.accessibility;

import edu.swjtu.excel.StaffExcel;
import edu.swjtu.file.FileUpload;
import edu.swjtu.impl.BuffStaffDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.impl.SynchDaoImpl;
import edu.swjtu.model.BuffStaff;
import edu.swjtu.model.Staff;
import edu.swjtu.model.Synch;
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
		  response.setContentType("text/html;charset=UTF-8");
		DBUtil db = new DBUtil();
		PrintWriter out = response.getWriter();
		if(request.getParameter("type").equals("0")){
			try {
					Connection con = db.getCon();
					StaffDaoImpl sdi = new StaffDaoImpl();
					String number = request.getParameter("number");
					Staff staff = sdi.getStaffByNumber(number, con);
					BuffStaffDaoImpl bsdi = new BuffStaffDaoImpl();
					BuffStaff bstaff = bsdi.getBuffStaffbyNumber(number, con);
					if(null != staff)
						out.print(1);
					else if(bstaff != null)
						out.print(2);
					else 
						out.print(3);
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
				String lati = request.getParameter("staff_lati");
				String longti = request.getParameter("staff_long");
				Staff staff = sdi.getStaffByNumber(number, con);
				Staff staff1 = new Staff();
				staff1.setNumber(number);
				staff1.setName(name);
				staff1.setDepartment(department);
				staff1.setGroup(group);;
				staff1.setAddress(address);
				staff1.setLati(Double.parseDouble(lati));
				staff1.setLongti(Double.parseDouble(longti));
				staff1.setSiteId(-1);
				sdi.addOneStaff(staff1, con);
				SynchDaoImpl sydi = new SynchDaoImpl();
				Synch synch = new Synch();
				synch.setName((String)request.getSession().getAttribute("userName")==null?"管理员":(String)request.getSession().getAttribute("userName"));
				Date date1 = new Date();     
				 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
				 String str1 = sdf1.format(date1); 
				synch.setTime(str1);
				sydi.addSynch(synch, con);
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
				System.out.print(1);
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
							int flag1 = 0 , flag2=0;
							BuffStaffDaoImpl bsdi = new BuffStaffDaoImpl();
							StaffDaoImpl sdi = new StaffDaoImpl();
							for(Staff staff : staffList){
								if(sdi.getStaffByNumber(staff.getNumber(), con) !=null){
									flag1 = 1;
									break;
								}	
								if(bsdi.getBuffStaffbyNumber(staff.getNumber(), con) !=null){
									flag2 = 1;
									break;
								}
							
							}
							if(flag1 == 1){
								out.print(4);
							}
							else if(flag2 ==1){
								out.print(7);
							}	
							else{
								
								
							/*	*/
								
								JSONObject jo= new JSONObject();
								jo.put("stafflist", staffList);
								
								out.print(jo.toString());
								
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
				String lati = request.getParameter("staff_lati");
				String longti = request.getParameter("staff_long");
				bstaff.setLati(Double.parseDouble(lati));
				bstaff.setLongti(Double.parseDouble(longti));
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
						int flag1 = 0 , flag2 = 0;
						StaffDaoImpl sdi = new StaffDaoImpl();
						BuffStaffDaoImpl bsdi = new BuffStaffDaoImpl();
						for(Staff staff : staffList){
							if(sdi.getStaffByNumber(staff.getNumber(), con) !=null){
								flag1 = 1;
								break;
							}	
							if(bsdi.getBuffStaffbyNumber(staff.getNumber(), con) !=null){
								flag2 = 1;
								break;
							}
						}
						if(flag1 == 1){
							out.print(4);
						}
						else if(flag2 == 1)
							out.print(7);
						else{	
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
							JSONObject jo= new JSONObject();
							jo.put("bstafflist", bstaffList);
							
							out.print(jo.toString());
							
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
		    	String type = ct.getTime();
		    	 ServletContext application = this.getServletContext();
		       application.setAttribute("quartz_type", type); 
				out.print(1);
				//System.out.println( request.getSession().getServletContext().getRealPath("/"));
				out.close();
		
		}
		else if(request.getParameter("type").equals("6")){		
			
			StaffDaoImpl sdi = new StaffDaoImpl();
			Connection con;
			try {
				con = db.getCon();
				
				String jo  = request.getParameter("staffs");
				System.out.println(jo);
				JSONArray jsono = JSONArray.fromObject(jo);
				ArrayList<Staff> stafflist = new ArrayList<Staff>();
				for(int i=0;i<jsono.size();i++){
					JSONObject jso = (JSONObject) jsono.get(i);
					Staff staff = (Staff) JSONObject.toBean(jso,Staff.class);
					stafflist.add(staff);
				}
				sdi.addListStaff(stafflist, con);
				SynchDaoImpl sydi = new SynchDaoImpl();
				Synch synch = new Synch();
				synch.setName((String)request.getSession().getAttribute("userName")==null?"管理员":(String)request.getSession().getAttribute("userName"));
				Date date1 = new Date();     
				 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
				 String str1 = sdf1.format(date1); 
				synch.setTime(str1);
				sydi.addSynch(synch, con);
				con.close();
				out.print(1);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			else if(request.getParameter("type").equals("7")){		
				
				StaffDaoImpl sdi = new StaffDaoImpl();
				Connection con;
				try {
					con = db.getCon();
					
					String jo  = request.getParameter("staffs");
					System.out.println(jo);
					JSONArray jsono = JSONArray.fromObject(jo);
					ArrayList<BuffStaff> stafflist = new ArrayList<BuffStaff>();
					for(int i=0;i<jsono.size();i++){
						JSONObject jso = (JSONObject) jsono.get(i);
						BuffStaff buffStaff = (BuffStaff) JSONObject.toBean(jso,BuffStaff.class);
						stafflist.add(buffStaff);
					}
					BuffStaffDaoImpl bsdi = new BuffStaffDaoImpl();
					bsdi.addListBuffStaff(stafflist, con);
					
					con.close();
					out.print(1);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	
			}
		}

}
