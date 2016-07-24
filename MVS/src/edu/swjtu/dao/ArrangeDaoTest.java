package edu.swjtu.dao;

import java.sql.SQLException;

import org.junit.Test;

import edu.swjtu.impl.ArrangeDaoImpl;
import edu.swjtu.model.Arrange;
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
		.getCon(),"2015-05"));
	}
	/**
	 * 测试删除byId
	 * 2016年7月15日上午9:37:31
	 * @author jimolonely
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testDelById() throws ClassNotFoundException, SQLException{
		System.out.println(new ArrangeDaoImpl().delArrById(new DBUtil().getCon(), 10));
	}
	
	@Test
	public void testGetAllByMonth() throws ClassNotFoundException, SQLException{
		System.out.println((new ArrangeDaoImpl().getAllMonthArr(new DBUtil()
		.getCon(),"2015-05")).size());
	}
	/**
	 * 测试增加
	 * 2016年7月17日上午10:04:12
	 * @author jimolonely
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void textAdd() throws ClassNotFoundException, SQLException{
		Arrange arr = new Arrange();
		arr.setArrangeId(100);
		arr.setName("班次来了");
		arr.setLineId(1);
		arr.setCarId(2);
		arr.setTime("2:00");
		arr.setDate("2014-02-19");
		System.out.println(new ArrangeDaoImpl().addArr(arr, new DBUtil().getCon()));
	}
	/**
	 * 测试取得某日的所有
	 * 2016年7月24日上午11:05:41
	 * @author jimolonely
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testGetAllByDate() throws ClassNotFoundException, SQLException{
		System.out.println((new ArrangeDaoImpl().getAllArrByDate(new DBUtil()
		.getCon(),"2014-02-19")).size());
	}
	/**
	 * 测试取得某天的一个班次
	 * 2016年7月24日下午12:45:07
	 * @author jimolonely
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testGetByDateAndTime() throws ClassNotFoundException, SQLException{
		System.out.println((new ArrangeDaoImpl().getArrByDateAndTime(new DBUtil()
		.getCon(),"2017-01-01","09:00")).getArrangeId());
	}
}
