package edu.swjtu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.model.DayRecord;

public interface DayRecordDao {
	/**
	 * 添加一份记录
	 * 2016年8月3日下午8:32:08
	 * @author jimolonely
	 * @return
	 * @throws SQLException
	 */
	public int addOneRecord(Connection con,DayRecord record) throws SQLException;
	/**
	 * 清空所有记录
	 * 2016年8月3日下午8:34:00
	 * @author jimolonely
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public int clearRecord(Connection con) throws SQLException;
	/**
	 * 根据站点id找到所有记录
	 * 2016年8月3日下午8:35:25
	 * @author jimolonely
	 * @param con
	 * @param siteId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<DayRecord> getRecordBySiteId(Connection con,int siteId) throws SQLException;
}
