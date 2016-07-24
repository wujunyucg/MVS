package edu.swjtu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
	/**
	 * 得到所有线路
	 * 2016年7月23日下午8:45:48
	 * @author jimolonely
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Line> getAllLine(Connection con) throws SQLException;
	/**
	 * 更新Line
	 * 2016年7月24日下午12:50:40
	 * @author jimolonely
	 * @param con
	 * @param line
	 * @return
	 * @throws SQLException
	 */
	public int updateLine(Connection con,Line line) throws SQLException;
}
