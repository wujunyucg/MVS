package edu.swjtu.dao;

import java.sql.Connection;
import java.sql.SQLException;

import edu.swjtu.model.Line;

public interface LineDao {
	/**
	 * 根据id取得线路
	 * 2016年7月14日上午9:52:23
	 * @author jimolonely
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public Line getLineById(Connection con,int id) throws SQLException;
	/**
	 * 根据名称找到Line
	 * 2016年7月17日上午10:16:31
	 * @author jimolonely
	 * @param con
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public Line getLineByName(Connection con,String name) throws SQLException;
}
