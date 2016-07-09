package edu.swjtu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.model.Power;
import edu.swjtu.model.User;

/**
 * 
 * 2016年7月9日上午11:17:58
 * @author mischief
 * TODO	权限
 */

public interface PowerDao {
	
	public int addPower(Power power,Connection con) throws SQLException;
	
	public int deletePower(int powerId,Connection con) throws SQLException;
	
	public int updatePower(Power power,Connection con) throws SQLException;
	
	/**
	 * 
	 * 2016年7月9日上午11:19:44
	 * 按ID查
	 * @author mischief7
	 * @param powerId
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public Power getPowerById(int powerId,Connection con) throws SQLException;
	
	/**
	 * 
	 * 2016年7月9日上午11:20:06
	 *查找所有
	 * @author mischief7
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public ArrayList getAllPower(Connection con) throws SQLException;
	
	
}
