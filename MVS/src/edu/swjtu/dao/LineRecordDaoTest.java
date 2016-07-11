package edu.swjtu.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import edu.swjtu.impl.LineRecordDaoImpl;
import edu.swjtu.impl.PowerDaoImpl;
import edu.swjtu.model.LineRecord;
import edu.swjtu.model.Power;
import edu.swjtu.util.DBUtil;

public class LineRecordDaoTest {
	@Test
	public void testAddCarRecord() throws ClassNotFoundException, SQLException{
		LineRecord carrec = new LineRecord();
		for(int i=0;i<10;i++){	
			carrec.setLineId(i + 5);
			Date now=new Date();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			carrec.setDate(dateFormat.format(now));
			carrec.setNum(i + 1);
			carrec.setRate((i + 52) / 101.0);
			carrec.setStaffIds("584" + i);	
			
			System.out.println(new LineRecordDaoImpl().addLineRecord(carrec, new DBUtil().getCon()));//1
		}		
	}
	
	@Test
	public void testDeleteCarReocrd() throws ClassNotFoundException, SQLException{
		LineRecordDaoImpl testdelete = new LineRecordDaoImpl();
		int u = testdelete.deleteLineRecord(7, new DBUtil().getCon());
		System.out.println(u);
	}
	
	@Test
	public void testUpdataCarRecord() throws ClassNotFoundException, SQLException{
		LineRecord carrec = new LineRecord();
		
		int i = 7;
		carrec.setLinerecordId(6);
		carrec.setLineId(i + 5);
		Date now=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		carrec.setDate(dateFormat.format(now));
		carrec.setNum(i + 1);
		carrec.setRate((i + 52) / 101.0);
		carrec.setStaffIds("584" + i);		
		
		int u = new LineRecordDaoImpl().updateLineRecord(carrec, new DBUtil().getCon());
		System.out.println(u);//1
	}
	
	@Test
	public void testFindAllUser() throws ClassNotFoundException, SQLException{
		 ArrayList<LineRecord> list = new LineRecordDaoImpl().getAllLineRecord(new DBUtil().getCon());
		 for(int i=0;i<list.size();i++)
			 System.out.println(list.get(i).getRate());
	}
	
	@Test
	public void testFindCarRecord() throws ClassNotFoundException, SQLException{
		LineRecord carrec = new LineRecordDaoImpl().getLineRecordById(2, new DBUtil().getCon());
		 System.out.println(carrec.getRate());
	}
}
