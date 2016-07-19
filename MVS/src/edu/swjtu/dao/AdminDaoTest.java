package edu.swjtu.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.Staff;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;



public class AdminDaoTest {
	String xmlPath = "C:/Users/asus1/git/MVS/MVS/src/quartz_job.xml";
	@SuppressWarnings("unchecked")
	@Test
	public void test() throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlPath); // 使用dom解析xml文件
            NodeList sonlist = doc.getElementsByTagName("cron"); 
            int flag = 0;
            Element son = null;
            for (int i = 0; i < sonlist.getLength(); i++) // 循环处理对象
            {
                son = (Element)sonlist.item(i);;
                
                for (Node node = son.getFirstChild(); node != null; node = node.getNextSibling()){  
                    if (node.getNodeType() == Node.ELEMENT_NODE ){  
                        String name = node.getNodeName();  
                        String value = node.getFirstChild().getNodeValue();
                        if(name.equals("name") && value.equals("triger1"))
                        {
                        	flag = 1;
                        	break;
                        }
                    }  
                }  
               if(flag == 1)
            	   break;
            }
            if(flag==1){
            	son.getElementsByTagName("cron-expression").item(0).setTextContent("0 52 20 * * ?");
            	TransformerFactory factory = TransformerFactory.newInstance();
                Transformer former = factory.newTransformer();
                former.transform(new DOMSource(doc), new StreamResult(new File(xmlPath)));
            }
        } catch (Exception e) {
            e.printStackTrace();
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

