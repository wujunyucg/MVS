package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
	public int deleteSiteRecordBySiteId(int siteId, Connection con)
			throws SQLException {
		String sql = "delete  from siterecord where siteRecord_siteId = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, siteId);
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
	
	public static String[] dayForWeek(String pTime) throws Exception {
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  Calendar c = Calendar.getInstance();
		  c.setTime(format.parse(pTime));
		  int dayForWeek = 0;
		  if(c.get(Calendar.DAY_OF_WEEK) == 1){
		   dayForWeek = 7;
		  }else{
		   dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		  }
		  String [] week = new String[7];
		  Date  date = format.parse(pTime);
		  for(int i=dayForWeek;i>0;i--){
			  week[i-1]= format.format(new Date(date.getTime() +(i-dayForWeek) * 24 * 60 * 60 * 1000));
		  }
		  for(int i=dayForWeek;i<8;i++){
			  week[i-1]= format.format(new Date(date.getTime() +(i-dayForWeek) * 24 * 60 * 60 * 1000));
		  }
		  return week;
		 }
	
	@Override
	public ArrayList<SiteRecord> getSiteRecordByDate(int type,String date,Connection con) throws Exception {
		ArrayList<SiteRecord> siterecList = new ArrayList<SiteRecord>(); 
		String sql =null;
		
	
		try {
			PreparedStatement pstm ;
			if(type==1){
				//System.out.println(date);
				 sql = "select * from siterecord where siteRecord_date = ?";
				
				 pstm = con.prepareStatement(sql);
				 pstm.setString(1, date);
				ResultSet rs = pstm.executeQuery();
				while(rs.next()){
					siterecList.add(getSiteRecordOne(rs));
				}
			}
			else if(type==2){
						String []week= dayForWeek(date);
						for(int i=0;i<7;i++){
							sql = "select * from siterecord where siteRecord_date = ?";
							 pstm = con.prepareStatement(sql);
							 pstm.setString(1, week[i]);
							 ResultSet rs = pstm.executeQuery();
							 while(rs.next()){
									siterecList.add(getSiteRecordOne(rs));
								}
						}
						 
			}
			else if(type==3){
				String []s = date.split("-");
				date=s[0]+"-"+s[1];
				 sql = "select * from siterecord where siteRecord_date like ?";
				 pstm = con.prepareStatement(sql);
				 pstm.setString(1, "%"+date+"%");
				ResultSet rs = pstm.executeQuery();
				while(rs.next()){
					siterecList.add(getSiteRecordOne(rs));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return siterecList;
	}

}
