package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import edu.swjtu.dao.DayRecordDao;
import edu.swjtu.model.DayRecord;

public class DayRecordDaoImpl implements DayRecordDao{

	public DayRecord getOneRecord(ResultSet rs) throws SQLException{
		DayRecord r = new DayRecord();
		r.setDay_date(rs.getString("day_date"));
		r.setDay_siteId(rs.getInt("day_siteId"));
		r.setDay_staffNumber(rs.getString("day_staffNum"));
		r.setDayId(rs.getInt("day_id"));
		r.setDay_lineId(rs.getInt("day_lineId"));
		return r;
	}
	
	@Override
	public int addOneRecord(Connection con, DayRecord record)
			throws SQLException {
		String sql = "insert into dayrecord values(null,?,?,?,?)";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, record.getDay_staffNumber());
		pstm.setInt(2, record.getDay_siteId());
		pstm.setString(3, record.getDay_date());
		pstm.setInt(4, record.getDay_lineId());
		return pstm.executeUpdate();
	}

	@Override
	public int clearRecord(Connection con) throws SQLException {
		String sql = "delete from dayrecord";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public HashSet<DayRecord> getRecordBySiteId(Connection con, int siteId)
			throws SQLException {
		HashSet<DayRecord> list = new HashSet<DayRecord>();
		String sql = "select * from dayrecord where day_siteId = ?";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setInt(1, siteId);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()){
			list.add(getOneRecord(rs));
		}
		return list;
	}
	
	@Override
	public HashSet<DayRecord> getRecordByLineId(Connection con, int lineId)
			throws SQLException {
		HashSet<DayRecord> list = new HashSet<DayRecord>();
		String sql = "select * from dayrecord where day_lineId = ?";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setInt(1, lineId);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()){
			list.add(getOneRecord(rs));
		}
		return list;
	}

}
