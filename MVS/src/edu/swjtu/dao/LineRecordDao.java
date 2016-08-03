package edu.swjtu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.model.LineRecord;

/**
 * 路线记录
 * 2016年7月11日下午3:08:51
 * @author mischief
 * TODO
 */
public interface LineRecordDao {
	public int addLineRecord(LineRecord linerecord,Connection con) throws SQLException;
	
	public int deleteLineRecord(int linerecordId,Connection con) throws SQLException;
	
	public int updateLineRecord(LineRecord linerecord,Connection con) throws SQLException;
	
	public LineRecord getLineRecordById(int linerecordId,Connection con) throws SQLException;
	
	public ArrayList<LineRecord> getAllLineRecord(Connection con) throws SQLException;
	
	public ArrayList<LineRecord> getLineRecordByDayDate(Connection con, String date) throws SQLException;
	
	public ArrayList<LineRecord> getLineRecordByWeekDate(Connection con,String date) throws SQLException, Exception;
	
	public ArrayList<LineRecord> getLineRecordByMonthDate(Connection con,String date) throws SQLException;
}
