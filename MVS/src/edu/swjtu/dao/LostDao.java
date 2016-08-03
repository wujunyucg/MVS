package edu.swjtu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.model.Arrange;
import edu.swjtu.model.Lost;

public interface LostDao {

	/**
	 * 取得所有失物
	 * 2016年8月2日下午12:41:54
	 * @author jimolonely
	 * @param con
	 * @return
	 */
	public ArrayList<Lost> getAllLost(Connection con);
	/**
	 * 添加爱一个lost
	 * 2016年8月2日下午12:42:41
	 * @author jimolonely
	 * @param con
	 * @param lost
	 * @return
	 * @throws SQLException 
	 */
	public int addOneLost(Connection con,Lost lost) throws SQLException;
	/**
	 * 分页取得lost
	 * 2016年8月2日下午1:00:43
	 * @author jimolonely
	 * @param con
	 * @param startPage
	 * @param pageNum
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Lost> getPageLost(Connection con, int startPage,
			int pageNum) throws SQLException;
	/**
	 * 取得不同类别的lost
	 * 2016年8月2日下午4:28:41
	 * @author jimolonely
	 * @param con
	 * @param startPage
	 * @param pageNum
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Lost> getPageLost(Connection con, int startPage,
			int pageNum,String type) throws SQLException;
}
