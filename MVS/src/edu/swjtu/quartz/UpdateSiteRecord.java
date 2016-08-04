package edu.swjtu.quartz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import edu.swjtu.impl.DayRecordDaoImpl;
import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.impl.LineRecordDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.SiteRecordDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.DayRecord;
import edu.swjtu.model.Line;
import edu.swjtu.model.LineRecord;
import edu.swjtu.model.Site;
import edu.swjtu.model.SiteRecord;
import edu.swjtu.util.DBUtil;
import edu.swjtu.util.DateUtil;

public class UpdateSiteRecord {
	
	public void update(){
		DayRecordDaoImpl drd = new DayRecordDaoImpl();
		SiteRecordDaoImpl srd = new SiteRecordDaoImpl();
		LineRecordDaoImpl lrd = new LineRecordDaoImpl();
		StaffDaoImpl fdi = new StaffDaoImpl();
		SiteDaoImpl sdi = new SiteDaoImpl();
		Connection con = null;
		DBUtil db = new DBUtil();
		
		try {
			con = db.getCon();
			//更新每日站点记录
			ArrayList<Site> sites = sdi.getAllSite(con);
			for(Site s:sites){
				HashSet<DayRecord> rs = drd.getRecordBySiteId(con, s.getSiteId());
				SiteRecord sr = new SiteRecord();
				sr.setDate(DateUtil.getDate());
				int num = rs.size();
				if(num>0){//如果这个站点有人上车
					StringBuffer sb = new StringBuffer("");
					for(DayRecord d:rs){
						sb.append(d.getDay_staffNumber()+",");
					}
					int numshould = s.getPeoNum();
					double rate = num*1.0/numshould;
					sr.setNum(num);
					sr.setRate(rate);
					sr.setStaffIds(sb.substring(0,sb.length()-1));
					sr.setSiteId(s.getSiteId());
				}else{
					sr.setNum(0);
					sr.setRate(0);
					sr.setStaffIds("");
					sr.setSiteId(s.getSiteId());
				}
				srd.addSiteRecord(sr, con);
			}
			
			//更新每日线路记录
			LineDaoImpl ldi = new LineDaoImpl();
			ArrayList<Line> lines = ldi.getAllLine(con);
			for(Line l:lines){
				HashSet<DayRecord> rs = drd.getRecordByLineId(con, l.getLineId());
				LineRecord lr = new LineRecord();
				lr.setDate(DateUtil.getDate());
				int num = rs.size();
				if(num>0){//如果条线路有人上车
					StringBuffer sb = new StringBuffer("");
					for(DayRecord d:rs){
						sb.append(d.getDay_staffNumber()+",");
					}
					int numshould = l.getNum();
					double rate = num*1.0/numshould;
					lr.setNum(num);
					lr.setRate(rate);
					lr.setStaffIds(sb.substring(0,sb.length()-1));
					lr.setLineId(l.getLineId());
				}else{
					lr.setNum(0);
					lr.setRate(0);
					lr.setStaffIds("");
					lr.setLineId(l.getLineId());
				}
				lrd.addLineRecord(lr, con);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			if(null!=con){
				try {
					db.closeCon(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

