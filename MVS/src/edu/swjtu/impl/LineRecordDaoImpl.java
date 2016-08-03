package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.LineRecordDao;
import edu.swjtu.model.Car;
import edu.swjtu.model.LineRecord;
import edu.swjtu.model.SiteRecord;

/**
 * LineRecord实现
 * 2016年7月11日下午3:11:11
 * @author mischief
 * TODO
 */
public class LineRecordDaoImpl implements LineRecordDao {

	private LineRecord getLineRecordOne(ResultSet rs) throws SQLException{
		LineRecord linerec = new LineRecord();
		linerec.setLinerecordId(rs.getInt("lineRecord_id"));
		linerec.setLineId(rs.getInt("lineRecord_lineId"));
		linerec.setDate(rs.getString("lineRecord_date"));
		linerec.setNum(rs.getInt("lineRecord_num"));
		linerec.setRate(rs.getDouble("lineRecord_rate"));
		linerec.setStaffIds(rs.getString("lineRecord_staffIds"));
		return linerec;
	}
	
	@Override
	public int addLineRecord(LineRecord linerecord, Connection con)
			throws SQLException {
		String sql = "insert into linerecord values(null,?,?,?,?,?)";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setInt(1, linerecord.getLineId());
		pstm.setString(2, linerecord.getDate());
		pstm.setInt(3, linerecord.getNum());
		pstm.setDouble(4, linerecord.getRate());
		pstm.setString(5, linerecord.getStaffIds());
		
		return pstm.executeUpdate();
	}

	@Override
	public int deleteLineRecord(int linerecordId, Connection con)
			throws SQLException {
		String sql = "delete  from linerecord where lineRecord_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, linerecordId);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int updateLineRecord(LineRecord linerecord, Connection con)
			throws SQLException {
		String sql = "update  linerecord set lineRecord_lineId = ? ,lineRecord_date = ? ,"
				+ "lineRecord_num = ? ,lineRecord_rate = ? ,lineRecord_staffIds = ?"
				+ " where lineRecord_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, linerecord.getLineId());
			pstm.setString(2, linerecord.getDate());
			pstm.setInt(3, linerecord.getNum());
			pstm.setDouble(4, linerecord.getRate());
			pstm.setString(5, linerecord.getStaffIds());
			pstm.setInt(6, linerecord.getLinerecordId());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public LineRecord getLineRecordById(int linerecordId, Connection con)
			throws SQLException {
		LineRecord linerec = null;
		String sql = "select * from linerecord where lineRecord_id=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, linerecordId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				linerec = getLineRecordOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return linerec;
	}

	@Override
	public ArrayList<LineRecord> getAllLineRecord(Connection con) throws SQLException {
		ArrayList<LineRecord> linerecList = new ArrayList<LineRecord>(); 
		String sql = "select * from linerecord";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				linerecList.add(getLineRecordOne(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return linerecList;
	}

	@Override
	public ArrayList<LineRecord> getLineRecordByDayDate(Connection con,String date) throws SQLException {
		ArrayList<LineRecord> list = new ArrayList<LineRecord>();
		String sql = "select * from linerecord where lineRecord_date=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, date);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				list.add(getLineRecordOne(rs)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public ArrayList<LineRecord> getLineRecordByWeekDate(Connection con,
			String date) throws Exception {
		String sql =null;
		PreparedStatement pstm;
		ArrayList<LineRecord> linerecList = new ArrayList<LineRecord>(); 
		String []week= new SiteRecordDaoImpl().dayForWeek(date);
		for(int i=0;i<7;i++){
			sql = "select * from linerecord where lineRecord_date = ?";
			 pstm = con.prepareStatement(sql);
			 pstm.setString(1, week[i]);
			 ResultSet rs = pstm.executeQuery();
			 while(rs.next()){
				 linerecList.add(getLineRecordOne(rs));
				}
		}
		return linerecList;
	}

	@Override
	public ArrayList<LineRecord> getLineRecordByMonthDate(Connection con,
			String date) throws SQLException {
		ArrayList<LineRecord> linerecList = new ArrayList<LineRecord>(); 
		String sql = "select *from linerecord where lineRecord_date like '%" + date + "%'";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			linerecList.add(getLineRecordOne(rs));
		}
		return linerecList;
	}

}
