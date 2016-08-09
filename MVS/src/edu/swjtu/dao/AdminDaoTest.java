package edu.swjtu.dao;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
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
	public void test() throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException, ParseException, ParsePropertyException, InvalidFormatException {
		// 创建柱状图  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        // 装载数据  
        dataset.setValue(6, "Profit", "Jane");  
        dataset.setValue(3, "Profit2", "Jane");  
        dataset.setValue(7, "Profit", "Tom");  
        dataset.setValue(6, "Profit2", "Tom");  
        dataset.setValue(8, "Profit", "Jill");  
        dataset.setValue(9, "Profit2", "Jill");  
        dataset.setValue(5, "Profit", "Johh");  
        dataset.setValue(8, "Profit2", "Johh");  
        dataset.setValue(12, "Profit", "Fred");  
        dataset.setValue(11, "Profit2", "Fred");  
        // 产生柱状图  
        // JFreeChart chart = ChartFactory.createBarChart("标题", "x轴标志", "y轴标志",  
        // 设置数据, 设置图形显示方向, 是否显示图形, 是否进行提示, 是否配置报表存放地址);  
  
        // 3D柱状图  
        JFreeChart chart = ChartFactory.createBarChart("站点统计报表", "站点", "搭乘人数",  
                dataset, PlotOrientation.VERTICAL, true, true, false);  
        // 解决中文乱码  
        CategoryPlot plot = chart.getCategoryPlot();  
        CategoryAxis domainAxis = plot.getDomainAxis();  
        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();  
  
        TextTitle textTitle = chart.getTitle();  
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));  
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
        numberAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
        numberAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
  
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));  
  
        try {  
            
            // 保存图片到指定位置  
             ChartUtilities.saveChartAsJPEG(new File("d:\\bar.png"), chart, 
            500,  
            300);  
        } catch (Exception e) {  
            System.err.println("Problem occurred creating chart.");  
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

