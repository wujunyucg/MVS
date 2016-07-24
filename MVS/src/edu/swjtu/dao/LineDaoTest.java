package edu.swjtu.dao;

import java.sql.SQLException;

import org.junit.Test;

import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.model.Line;
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
		line.setCarId(1);
		line.setLineId(1);
		line.setName("线路1");
		line.setNum(40);
		line.setSiteId("1,2,3");
		System.out.println(new LineDaoImpl().updateLine(new DBUtil().getCon(),line));
	}
}
