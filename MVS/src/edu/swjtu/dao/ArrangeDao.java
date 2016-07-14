package edu.swjtu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.model.Arrange;

public interface ArrangeDao {
	/**
	 * 取得所有排班表
	 * 2016年7月13日下午10:06:53
	 * @author jimolonely
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<Arrange> getAllArrange(Connection con) throws SQLException;
	/**
	 * 班次的分页查询
	 * 2016年7月14日上午9:26:59
	 * @author jimolonely
	 * @param con
	 * @param startPage
	 * @param pageNum
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<Arrange> getPageArrange(Connection con,int startPage,int pageNum) throws SQLException;
	/**
	 * 取得所有条数
	 * 2016年7月14日上午9:35:39
	 * @author jimolonely
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public int getTotal(Connection con) throws SQLException;
}
