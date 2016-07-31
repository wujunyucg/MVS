package edu.swjtu.file;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.SiteRecordDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Site;
import edu.swjtu.model.SiteRecord;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;

public class ProduceData {

	@Test
	public void siteRecordData() throws ClassNotFoundException, SQLException {
		SiteDaoImpl sdi = new SiteDaoImpl();
		StaffDaoImpl fdi = new StaffDaoImpl();
		SiteRecordDaoImpl rdi = new SiteRecordDaoImpl();
		DBUtil db = new DBUtil();
		Connection con = db.getCon();
		ArrayList<Site> sites = sdi.getAllSite(con);

		for (Site s : sites) {
			int siteId = s.getSiteId();
			ArrayList<Staff> sf = fdi.getStaffBySiteId(siteId, con);
			StringBuffer sb = new StringBuffer("");
			for (Staff f : sf) {
				sb.append(f.getStaffId() + ",");
			}
			int len = sf.size();
			System.out.println(sb+" ==="+len);
			
			for(int i=1;i<=9;i++){
				for(int j=1;j<=30;j++){
					String date = "2015-0"+i+"-";
					if(j<10){
						date+="0"+j;
					}else{
						date+=j+"";
					}
					SiteRecord sr = new SiteRecord();
					sr.setDate(date);
					sr.setSiteId(siteId);
					sr.setStaffIds(sb.substring(0,sb.length()-1));
					int num = 1; 
					if(5<len){
						num = (int)(len - Math.random()*5);
					}else{
						num = len;
					}
					sr.setNum(num);
					sr.setRate(num*1.0/len);
					rdi.addSiteRecord(sr, con);
				}
			}
		}
	}
}
