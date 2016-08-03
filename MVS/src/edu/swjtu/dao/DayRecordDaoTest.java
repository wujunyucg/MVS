package edu.swjtu.dao;

import java.sql.SQLException;

import org.junit.Test;

import edu.swjtu.impl.DayRecordDaoImpl;
import edu.swjtu.model.DayRecord;
import edu.swjtu.util.DBUtil;

public class DayRecordDaoTest {

	/**
	 * 测试添加一份记录
	 * 2016年8月3日下午8:47:25
	 * @author jimolonely
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testAddOne() throws ClassNotFoundException, SQLException{
		DayRecord r = new DayRecord();
		r.setDay_date("2012-01-02");
		r.setDay_siteId(5881);
		r.setDay_staffNumber("2014100001");
		System.out.println(new DayRecordDaoImpl().addOneRecord(
				new DBUtil().getCon(), r));
	}
	/**
	 * 测试根据siteId找
	 * 2016年8月3日下午8:57:18
	 * @author jimolonely
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testGetBySiteId() throws ClassNotFoundException, SQLException{
		System.out.println(
				new DayRecordDaoImpl().getRecordBySiteId(
						new DBUtil().getCon(), 5881).size());
	}
	/**
	 * 测试清空表
	 * 2016年8月3日下午8:59:00
	 * @author jimolonely
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testClear() throws ClassNotFoundException, SQLException{
		new DayRecordDaoImpl().clearRecord(new DBUtil().getCon());
	}
}
