package edu.swjtu.quartz;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import edu.swjtu.impl.BuffStaffDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.impl.SynchDaoImpl;
import edu.swjtu.model.BuffStaff;
import edu.swjtu.model.Staff;
import edu.swjtu.model.Synch;
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
					
				}	
				sdi.addListStaff(staffList, con);
				SynchDaoImpl sydi = new SynchDaoImpl();
				Synch synch = new Synch();
				synch.setName("定时同步");
				Date date1 = new Date();     
				 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
				 String str1 = sdf1.format(date1); 
				synch.setTime(str1);
				sydi.addSynch(synch, con);
				
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
