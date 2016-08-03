package edu.swjtu.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

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
	public void test() throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException, ParseException {
		DBUtil db = new DBUtil();
		Connection con = db.getCon();
		HashSet<String > s = new HashSet<String>();
		s.add("1");
		s.add("2");
		s.add("3");
		s.add("4");
		System.out.println(s.toString().replace("[", "").replace("]", "").replace(" ", ""));
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

