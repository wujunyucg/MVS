package edu.swjtu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.model.SiteRecord;

/**
 * 
 * 2016年7月11日下午4:02:37
 * @author mischief
 * TODO 站点记录Dao
 */
public interface SiteRecordDao {
public int addSiteRecord(SiteRecord siterecord,Connection con) throws SQLException;
	
	public int deleteSiteRecord(int siterecordId,Connection con) throws SQLException;
	
	public int updateSiteRecord(SiteRecord siterecord,Connection con) throws SQLException;
	
	public SiteRecord getSiteRecordById(int siterecordId,Connection con) throws SQLException;
	
	public ArrayList getAllSiteRecord(Connection con) throws SQLException;
}
