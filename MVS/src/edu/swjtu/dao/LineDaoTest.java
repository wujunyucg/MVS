package edu.swjtu.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.impl.LineRecordDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Line;
import edu.swjtu.model.LineRecord;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;

public class LineDaoTest {
	@Test
	public void testGetById() throws ClassNotFoundException, SQLException{
		System.out.println(new LineDaoImpl().getLineById(new DBUtil().getCon(), 1).getName());
	}
	/**
	 * 测试根据名称查找
	 * 2016年7月17日上午10:19:48
	 * @author jimolonely
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testGetByName() throws ClassNotFoundException, SQLException{
		System.out.println(new LineDaoImpl().getLineByName(new DBUtil().getCon(), "+").getLineId());
	}
	/**
	 * 测试取得所有线路
	 * 2016年7月23日下午8:48:32
	 * @author jimolonely
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testGetAll() throws ClassNotFoundException, SQLException{
		System.out.println(new LineDaoImpl().getAllLine(new DBUtil().getCon()).size());
	}
	
	@Test
	public void testUpdate() throws ClassNotFoundException, SQLException{
		Line line = new Line();
		line.setCarId("1");
		line.setLineId(728);
		line.setName("线路1");
		line.setNum(40);
		line.setSiteId("1,2,3");
		line.setRate(0.1);
		System.out.println(new LineDaoImpl().updateLine(new DBUtil().getCon(),line));
	}
	
	@Test
	public void testAddMore() throws ClassNotFoundException, SQLException{
		ArrayList<Line> linelist = new ArrayList<Line>();
		for(int i=0;i<3;i++){
			Line line = new Line();
			linelist.add(line);
			linelist.get(i).setCarId(i + ",5");
			linelist.get(i).setName("线路" + i + 1);
			linelist.get(i).setNum(40 + i);
			linelist.get(i).setSiteId("1,2,3" + "," + i);
			linelist.get(i).setRate((i+5) / 5.1 * 1.0);
		}
		System.out.println(new LineDaoImpl().addMoreLine(linelist, new DBUtil().getCon()));
	}
	@Test
	public void testDeleteAll() throws ClassNotFoundException, SQLException{
		System.out.println(new LineDaoImpl().deleteAllLine( new DBUtil().getCon()));
	}
	
	@Test
	public void testGAll() throws ClassNotFoundException, SQLException{
		ArrayList<LineRecord> list = new LineRecordDaoImpl().getAllLineRecord( new DBUtil().getCon());
		for(int i=0;i<list.size();i++)
			System.out.println(list.get(i).getStaffIds());
	}
	
	@Test
	public void testWeekDay() throws Exception{
		new LineRecordDaoImpl().getLineRecordByWeekDate(new DBUtil().getCon(), "2016-7-13");
	}
	
	@Test
	public void testLineRecord() throws ClassNotFoundException, SQLException{
		ArrayList<Line> linelist = null;
		ArrayList<Staff> stafflist = new StaffDaoImpl().getAllStaff(new DBUtil().getCon());
		linelist = new LineDaoImpl().getAllLine(new DBUtil().getCon());
		Date dt=new Date();     
		SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");     
		System.out.println();
		LineRecord rec = new LineRecord();
		for(int i=0;i<linelist.size();i++){
			
			rec.setDate(matter1.format(dt).toString());
			rec.setLineId(linelist.get(i).getLineId());
			rec.setNum(linelist.get(i).getNum());
			rec.setRate(linelist.get(i).getRate()*(-1.0));
			rec.setStaffIds("");
			for(int j=0;j<stafflist.size();j++){
				if(stafflist.get(j).getLineId() == linelist.get(i).getLineId()){
					System.out.println("111");
					rec.setStaffIds(rec.getStaffIds() + stafflist.get(j).getStaffId() + ",");
				}
			}
			if(rec.getStaffIds()!=null&&rec.getStaffIds().endsWith(",")){
				rec.setStaffIds(rec.getStaffIds().substring(0, rec.getStaffIds().length()-1));
			}
			System.out.println(rec.getStaffIds());
			new LineRecordDaoImpl().addLineRecord(rec, new DBUtil().getCon());
			
			
		}
		
		
		
	}
}
