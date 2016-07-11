package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.SiteRecordDao;
import edu.swjtu.model.SiteRecord;

public class SiteRecordDaoImpl implements SiteRecordDao {

	private SiteRecord getSiteRecordOne(ResultSet rs) throws SQLException{
		SiteRecord siterec = new SiteRecord();
		siterec.setSiterecordId(rs.getInt("siteRecord_id"));
		siterec.setSiteId(rs.getInt("siteRecord_siteId"));
		siterec.setDate(rs.getString("siteRecord_date"));
		siterec.setNum(rs.getInt("siteRecord_num"));
		siterec.setRate(rs.getDouble("siteRecord_rate"));
		siterec.setStaffIds(rs.getString("siteRecord_staffIds"));
		return siterec;
	}
	
	@Override
	public int addSiteRecord(SiteRecord siterecord, Connection con)
			throws SQLException {
		String sql = "insert into siterecord values(null,?,?,?,?,?)";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setInt(1, siterecord.getSiteId());
		pstm.setString(2, siterecord.getDate());
		pstm.setInt(3, siterecord.getNum());
		pstm.setDouble(4, siterecord.getRate());
		pstm.setString(5, siterecord.getStaffIds());
		
		return pstm.executeUpdate();
	}

	@Override
	public int deleteSiteRecord(int siterecordId, Connection con)
			throws SQLException {
		String sql = "delete  from siterecord where siteRecord_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, siterecordId);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int updateSiteRecord(SiteRecord siterecord, Connection con)
			throws SQLException {
		String sql = "update  siterecord set siteRecord_siteId = ? ,siteRecord_date = ? ,"
				+ "siteRecord_num = ? ,siteRecord_rate = ? ,siteRecord_staffIds = ?"
				+ " where siteRecord_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, siterecord.getSiteId());
			pstm.setString(2, siterecord.getDate());
			pstm.setInt(3, siterecord.getNum());
			pstm.setDouble(4, siterecord.getRate());
			pstm.setString(5, siterecord.getStaffIds());
			pstm.setInt(6, siterecord.getSiterecordId());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public SiteRecord getSiteRecordById(int siterecordId, Connection con)
			throws SQLException {
		SiteRecord siterec = null;
		String sql = "select * from siterecord where siteRecord_id=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, siterecordId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				siterec = getSiteRecordOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return siterec;
	}

	@Override
	public ArrayList getAllSiteRecord(Connection con) throws SQLException {
		ArrayList<SiteRecord> siterecList = new ArrayList<SiteRecord>(); 
		String sql = "select * from siterecord";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				siterecList.add(getSiteRecordOne(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return siterecList;
	}

}
