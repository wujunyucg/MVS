package edu.swjtu.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.internet.NewsAddress;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.swjtu.impl.AdminDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.Site;
import edu.swjtu.model.Staff;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;
import edu.swjtu.util.KMeans;



public class AdminDaoTest {
	String xmlPath = "C:/Users/asus1/git/MVS/MVS/src/quartz_job.xml";
	@SuppressWarnings("unchecked")
	@Test
	public void test() throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException {
		DBUtil db = new DBUtil();
		Connection con = db.getCon();
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		StaffDaoImpl sdi = new StaffDaoImpl();
		staffList = sdi.getAllStaff(new DBUtil().getCon());
		KMeans km = new KMeans(0);
		for(int i=1;;i++){
			km.setK(i);
			km.setDataSet(staffList);
			km.execute();
			ArrayList<ArrayList<Staff>> cluster = km.getCluster();
			ArrayList<double[]> center = km.getCenter();
			int f=0;
			for(int j =0;j<cluster.size();j++){
				for(int k=0;k<cluster.get(j).size();k++){
					if(km.GetDistance(cluster.get(j).get(k).getLati(), cluster.get(j).get(k).getLongti(), center.get(j)[0], center.get(j)[1])>1.0)
						f=1;
				}
			}
			if(f==1 )
				continue;
			for(int j =0;j<cluster.size();j++){
				if(cluster.get(j).size()==0){
					cluster.remove(j);
					center.remove(j);
				}
			}
			ArrayList<Site> siteList = new ArrayList<Site>();
			for(int j =0;j<center.size();j++){
				Site site = new Site();
				site.setLatitude(center.get(j)[0]);
				site.setLongitude(center.get(j)[1]);
				site.setBufftag(j);
				site.setAddress("chen");
				site.setName("123");
				site.setPeoNum(cluster.get(j).size());
				siteList.add(site);
			}
			SiteDaoImpl sdi1 = new SiteDaoImpl();
			sdi1.addListSite(siteList, con);
			siteList = sdi1.getAllSite(con);
			for(Site site:siteList){
				staffList.clear();
				for(int j=0;j<cluster.get(site.getBufftag()).size();j++){					
					Staff staff =new Staff();
					staff = cluster.get(site.getBufftag()).get(j);
					staff.setSiteId(site.getSiteId());
					staffList.add(staff);
				}
				sdi.updateListStaff(staffList, con);
			}
		//	for(int j=0;j<cluster.size();j++)
		//	{
				//km.printDataArray(cluster.get(i), "cluster["+i+"]");
		//	}
			System.out.print(cluster.size());
			break;
		}
	}
	
	/**
	 * 测试
	 * 2016年7月10日上午11:36:19
	 * @author jimolonely
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public void jimoTest() throws ClassNotFoundException, SQLException{
		String name = "1";
		System.out.println(new AdminDaoImpl().getAdminByName(name, new DBUtil().getCon()));
	}
	/**
	 * 测试分页语句功能
	 * 2016年7月13日下午1:01:58
	 * @author jimolonely
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testAdminPage() throws ClassNotFoundException, SQLException{
		System.out.println((new AdminDaoImpl().getPageAdmin(
				new DBUtil().getCon(), 2, 10)).get(0).getName());
	}
	@Test
	public void testAdminTotal() throws ClassNotFoundException, SQLException{
		System.out.println(new AdminDaoImpl().getTotal(new DBUtil().getCon()));
	}
}

