package edu.swjtu.dao;

import java.sql.SQLException;

import org.junit.Test;

import edu.swjtu.impl.LostDaoImpl;
import edu.swjtu.model.Lost;
import edu.swjtu.util.DBUtil;

public class LostDaoTest {

	@Test
	public void testAddOne() throws ClassNotFoundException, SQLException{
		Lost l = new Lost();
		l.setContent("捡到一只鞋子");
		l.setName("寂寞");
		l.setDate("2016-02-11");
		new LostDaoImpl().addOneLost(new DBUtil().getCon(), l);
	}
	
	@Test
	public void testGetByPage() throws ClassNotFoundException, SQLException{
		System.out.println(new LostDaoImpl().getPageLost(new DBUtil().getCon(), 1,10).size());
	}
	
	@Test
	public void testGetByPageType() throws ClassNotFoundException, SQLException{
		System.out.println(new LostDaoImpl().getPageLost(new DBUtil().getCon(), 1,10,"1").size());
	}
}
