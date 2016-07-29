package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.SiteDao;
import edu.swjtu.model.Car;
import edu.swjtu.model.Line;
import edu.swjtu.model.Site;
import edu.swjtu.model.Staff;

public class SiteDaoImpl implements SiteDao {

	private Site getSiteOne(ResultSet rs) throws SQLException{
		Site site = new Site();
		site.setAddress(rs.getString("site_address"));
		site.setDelay(rs.getInt("site_delay"));
		site.setLatitude(rs.getDouble("site_latitude"));
		site.setLineId(rs.getString("site_lineId"));
		site.setLongitude(rs.getDouble("site_longitude"));
		site.setName(rs.getString("site_name"));
		site.setOrder(rs.getString("site_order"));
		site.setPeoNum(rs.getInt("site_peoNum"));
		site.setSiteId(rs.getInt("site_id"));
		site.setBufftag(rs.getInt("site_bufftag"));
		site.setLineName(rs.getString("site_lineName"));
		return site;
	}
	
	private void getPreSta(PreparedStatement ps, Site site) throws SQLException{
		
		ps.setInt(1,site.getSiteId());
		ps.setDouble(2,site.getLatitude());
		ps.setDouble(3,site.getLongitude());
		ps.setString(4,site.getAddress());
		ps.setInt(5,site.getPeoNum());
		ps.setString(6,site.getName());
		ps.setString(7,site.getLineId());
		ps.setString(8,site.getOrder());
		ps.setInt(9,site.getDelay());
		ps.setInt(10,site.getBufftag());
		ps.setString(11,site.getLineName());
	}
	
	@Override
	public int addOneSite(Site site, Connection con) {
		// TODO Auto-generated method stub
		String sql = "insert into site (site_id,site_latitude,site_longitude,site_address,site_peoNum,site_name,site_lineId,site_order,site_delay,site_bufftag,site_lineName) values (? , ?,?,?,?,?,?,?,?,?,?)";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			getPreSta(pstm,site);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int addListSite(ArrayList<Site> siteList, Connection con) {
		Site site = new Site();
		String sql ="insert into site (site_id,site_latitude,site_longitude,site_address,site_peoNum,site_name,site_lineId,site_order,site_delay,site_bufftag,site_lineName) values (? , ?,?,?,?,?,?,?,?,?,?)";
		int [] rs = null;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			for(int i = 0; i<siteList.size(); i++){
				site = siteList.get(i);
				getPreSta(pstm,site);
				pstm.addBatch();
			}
			rs = pstm.executeBatch();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs[0];
	}

	@Override
	public int deleteOneSite(Site site, Connection con) {
		String sql = "delete  from site where site_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, site.getSiteId());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int deleteListSite(ArrayList<Site> siteList, Connection con) {
		String sql = "delete  from site where site_id = ? ";
		int [] rs = null;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			for(int i = 0; i<siteList.size(); i++){
				pstm.setInt(1, siteList.get(i).getSiteId());
				pstm.addBatch();
			}
			rs = pstm.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs[0];
	}
	
	@Override
	public int deleteAllSite(Connection con) {
		String sql = "delete  from site  where 1=1 ";
		int  rs = 0;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int updateSite(Site site, Connection con) {
		String sql = "update  site set site_id = ?,site_latitude = ?,site_longitude = ?,site_address = ?,site_peoNum = ?,site_name = ?,site_lineId = ?,site_order = ?,site_delay = ?,site_bufftag=?, site_lineName=? where site_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			getPreSta(pstm, site);
			pstm.setInt(12, site.getSiteId());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}
	
	@Override
	public int updateListSite(ArrayList<Site> siteList, Connection con) {
		String sql = "update  site set site_id = ?,site_latitude = ?,site_longitude = ?,site_address = ?,site_peoNum = ?,site_name = ?,site_lineId = ?,site_order = ?,site_delay = ?,site_bufftag=?,site_lineName=? where site_id = ?";
		int[] rs;
		Site site;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			for(int i = 0; i<siteList.size(); i++){
				site = siteList.get(i);
				getPreSta(pstm,site);
				pstm.setInt(12, site.getSiteId());
				pstm.addBatch();
			}
			rs = pstm.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs[0];
	}

	@Override
	public Site getSiteById(int siteId, Connection con) throws SQLException{
		String sql = "select*from site where site_id = ?";
		PreparedStatement pstm;
		pstm = con.prepareStatement(sql);
		pstm.setInt(1, siteId);
		ResultSet rs = pstm.executeQuery();
		Site site = null;
		if (rs.next()) {
			site = getSiteOne(rs);
		}
		return site;
	}

	@Override
	public Site getSiteByName(String name, Connection con) {
		String sql = "select * from site where site_name like ?";
		PreparedStatement pstm;
		Site site = null;
		ResultSet rs;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, "%"+name+"%");
			rs = pstm.executeQuery();
			if (rs.next()) {
				site = getSiteOne(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return site;
	}
	public ArrayList<Site> getListSiteByAddress(String address, Connection con) {
		String sql = "select * from site where site_address like ?";
		PreparedStatement pstm;
		ArrayList<Site> siteList = new ArrayList<Site>();
		ResultSet rs;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, "%"+address+"%");
			rs = pstm.executeQuery();
			while(rs.next()){
				siteList.add(getSiteOne(rs)) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return siteList;
	} 
	@Override
	public ArrayList<Site> getSiteByLineId(int lineId, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Site> getAllSite(Connection con) {
		ArrayList<Site> staffList = new ArrayList<Site>();
		String sql = "select * from site  ";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				staffList.add(getSiteOne(rs)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return staffList;
	}
	
	@Override
	public ArrayList<Site> getBuffSite(Connection con) {
		ArrayList<Site> staffList = new ArrayList<Site>();
		String sql = "select * from site where site_bufftag >-1 ";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				staffList.add(getSiteOne(rs)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return staffList;
	}

}
