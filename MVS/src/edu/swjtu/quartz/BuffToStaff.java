package edu.swjtu.quartz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import edu.swjtu.impl.BuffStaffDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.BuffStaff;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;


public class BuffToStaff implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException { 
		DBUtil db = new DBUtil();
		Connection con;
		try {
			con = db.getCon();
			BuffStaffDaoImpl  bsdi = new BuffStaffDaoImpl();
			ArrayList<BuffStaff> bstaffList = null;
			ArrayList<Staff> staffList = new ArrayList<Staff>();
			bstaffList = bsdi.getBuffStaff(con);
			StaffDaoImpl sdi = new StaffDaoImpl();
			if(bstaffList == null)
				return;
			else{
				for(BuffStaff bstaff:bstaffList){
					Staff staff = new Staff();
					staff.setAddress(bstaff.getAddress());
					staff.setDepartment(bstaff.getDepartment());
					staff.setGroup(bstaff.getGroup());
					staff.setName(bstaff.getName());
					staff.setNumber(bstaff.getNumber());
					staffList.add(staff);
					bsdi.updateBuffStaff(bstaff, con);
					System.out.println(bstaff.getId());
				}	
				sdi.addListStaff(staffList, con);
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}  
}
