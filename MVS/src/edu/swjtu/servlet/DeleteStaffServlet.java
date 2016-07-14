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
		
		try {
			con = db.getCon();
			if(onlyOne.equals("1")){
				int staffId = Integer.valueOf(request.getParameter("staffId")).intValue();
				sdi.deleteOneStaff(staffId, con);
				db.closeCon(con);
				
				out.print(1);
				out.close();
			}
			/*else if(onlyOne .equals("0")){
				 String status[] = request.getParameter("status").replace("[", "").replace("]", "").split(","); 
				 String userIds[] = request.getParameter("ids").replace("[", "").replace("]", "").split(","); 
				// System.out.println(a[0]);
				 for(String userId : userIds){
					 udi.deleteUser(Integer.valueOf(userId).intValue(), con);
				 }
				
				ArrayList<User> userList =new ArrayList<User>();
				userList=(ArrayList<User>) request.getSession().getAttribute("user_list");
				for(String statu :status){
					userList.remove(Integer.valueOf(statu).intValue());
				}
				
				db.closeCon(con);
				out.print(1);
				out.close();
			}*/
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
