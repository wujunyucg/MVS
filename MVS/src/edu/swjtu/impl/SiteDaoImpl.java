package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.SiteDao;
import edu.swjtu.model.Car;
import edu.swjtu.model.Site;
import edu.swjtu.model.Staff;

public class SiteDaoImpl implements SiteDao {

	private Site getSiteOne(ResultSet rs) throws SQLException{
		Site site = new Site();
		site.setAddress(rs.getString("site_address"));
		site.setDelay(rs.getInt("site_delay"));
		site.setLatitude(rs.getDouble("site_latitude"));
		site.setLineId(rs.getInt("site_lineId"));
		site.setLongitude(rs.getDouble("site_longitude"));
		site.setName(rs.getString("site_name"));
		site.setOrder(rs.getInt("site_order"));
		site.setPeoNum(rs.getInt("site_peoNum"));
		site.setSiteId(rs.getInt("site_id"));
		site.setBufftag(rs.getInt("site_bufftag"));
		return site;
	}
	
	private void getPreSta(PreparedStatement ps, Site site) throws SQLException{
		
		ps.setInt(1,site.getSiteId());
		ps.setDouble(2,site.getLatitude());
		ps.setDouble(3,site.getLongitude());
		ps.setString(4,site.getAddress());
		ps.setInt(5,site.getPeoNum());
		ps.setString(6,site.getName());
		ps.setInt(7,site.getLineId());
		ps.setInt(8,site.getOrder());
		ps.setInt(9,site.getDelay());
		ps.setInt(10,site.getBufftag());
	}
	
	@Override
	public int addOneSite(Site site, Connection con) {
		// TODO Auto-generated method stub
		String sql = "insert into site (site_id,site_latitude,site_longitude,site_address,site_peoNum,site_name,site_lineId,site_order,site_delay,site_bufftag) values (? , ?,?,?,?,?,?,?,?,?)";
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
		String sql ="insert into site (site_id,site_latitude,site_longitude,site_address,site_peoNum,site_name,site_lineId,site_order,site_delay,site_bufftag) values (? , ?,?,?,?,?,?,?,?,?)";
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteListSite(ArrayList<Site> siteList, Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSite(Site site, Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Site getSiteById(int siteId, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Site getSiteByName(String name, Connection con) {
		// TODO Auto-generated method stub
		return null;
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

}
