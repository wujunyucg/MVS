package edu.swjtu.dao;

import java.sql.SQLException;

import org.junit.Test;

import edu.swjtu.impl.LineDaoImpl;
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
}
