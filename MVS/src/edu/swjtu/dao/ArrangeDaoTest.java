package edu.swjtu.dao;

import java.sql.SQLException;

import org.junit.Test;

import edu.swjtu.impl.ArrangeDaoImpl;
import edu.swjtu.util.DBUtil;

public class ArrangeDaoTest {
	/**
	 * 测试查找所有班次数据
	 * 2016年7月14日上午9:10:19
	 * @author jimolonely
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testGetAll() throws ClassNotFoundException, SQLException{
		System.out.println((new ArrangeDaoImpl().getAllArrange(new DBUtil()
		.getCon())).get(0).getName());
	}
	/**
	 * 分页测试
	 * 2016年7月14日上午9:37:25
	 * @author jimolonely
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testGetPage() throws ClassNotFoundException, SQLException{
		System.out.println((new ArrangeDaoImpl().getPageArrange(new DBUtil()
		.getCon(),1,10)).get(0).getName());
	}
	/**
	 * 测试取得总数
	 * 2016年7月14日上午9:39:36
	 * @author jimolonely
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testGetTotal() throws ClassNotFoundException, SQLException{
		System.out.println(new ArrangeDaoImpl().getTotal(new DBUtil()
		.getCon()));
	}
	/**
	 * 测试取得某月班次
	 * 2016年7月14日下午5:39:03
	 * @author jimolonely
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testGetArrByMonth() throws ClassNotFoundException, SQLException{
		System.out.println((new ArrangeDaoImpl().getPageMonthArr(new DBUtil()
		.getCon(),1,10,"2015-5")).size());
	}
	/**
	 * 测试某月班次总数
	 * 2016年7月14日下午5:47:43
	 * @author jimolonely
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testGetTotalByMonth() throws ClassNotFoundException, SQLException{
		System.out.println(new ArrangeDaoImpl().getTotalByMonth(new DBUtil()
		.getCon(),"2015-5"));
	}
}
