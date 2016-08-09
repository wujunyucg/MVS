package edu.swjtu.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.imageio.ImageIO;
import javax.mail.internet.NewsAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.Region;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import sun.misc.BASE64Decoder;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONBuilder;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.SiteRecordDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Site;
import edu.swjtu.model.SiteRecord;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;

/**
 * 
 * ShowSiteServlet.java类
 * 2016年8月1日
 * @author wujunyu
 * TODo
 */
public class ShowSiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShowSiteServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		String date = request.getParameter("date");
		DBUtil db =new DBUtil();
		try {
			Connection con = db.getCon();
			SiteRecordDaoImpl srdi = new SiteRecordDaoImpl();
			SiteDaoImpl sdi = new SiteDaoImpl();
			StaffDaoImpl sdi1 = new StaffDaoImpl();
			if(type.equals("day")){
				PrintWriter out = response.getWriter();
				ArrayList<SiteRecord> srList = srdi.getSiteRecordByDate(1, date, con);
				ArrayList<Site> siteList = new ArrayList<Site>();
				for(SiteRecord  sr :srList){
					Site site = sdi.getSiteById(sr.getSiteId(), con);
					if(site!=null)
						siteList.add(site);
				}
				request.getSession().setAttribute("site_list", siteList);
				request.getSession().setAttribute("sr_list", srList);
				JSONObject jo =new JSONObject();
				jo.put("site_list", siteList);
				jo.put("sr_list", srList);
				out.print(jo.toString());
			}
			else if(type.equals("week")){
				PrintWriter out = response.getWriter();
				ArrayList<SiteRecord> srList = srdi.getSiteRecordByDate(2, date, con);
				String []week=srdi.dayForWeek(date);
				ArrayList<SiteRecord> srList1 = new ArrayList<SiteRecord>();
				 for (int i = 0; i < srList.size()-1; i++) {  
	            		for (int j = srList.size()-1; j > i; j--){
	            			
	            			if(srList.get(i).getSiteId()==srList.get(j).getSiteId()){
	            				srList.get(i).setNum(srList.get(i).getNum()+srList.get(j).getNum());
	            			/*	HashSet<String> set = new HashSet<String>();
	            				String [] ids =srList.get(i).getStaffIds().split(",");
	            				for(int k=0;k<ids.length;k++){
	            					set.add(ids[k]);
	            				}
	            				 ids =srList.get(j).getStaffIds().split(",");
	            				for(int k=0;k<ids.length;k++){
	            					set.add(ids[k]);
	            				}
	            				srList.get(i).setStaffIds(set.toString().replace("[", "").replace("]", "").replace(" ", ""));*/
	            				srList.remove(j);
	            			}
	            		}
	                } 
				ArrayList<Site> siteList = new ArrayList<Site>();
				for(SiteRecord  sr :srList){
					Site site = sdi.getSiteById(sr.getSiteId(), con);
					if(site!=null)
						siteList.add(site);
				}
				request.getSession().setAttribute("site_list", siteList);
				request.getSession().setAttribute("sr_list", srList);
				JSONObject jo =new JSONObject();
				jo.put("site_list", siteList);
				jo.put("sr_list", srList);
				out.print(jo.toString());
			}
			else if(type.equals("month")){
				PrintWriter out = response.getWriter();
				ArrayList<SiteRecord> srList = srdi.getSiteRecordByDate(3, date, con);
				String []week=srdi.dayForWeek(date);
				ArrayList<SiteRecord> srList1 = new ArrayList<SiteRecord>();
				 for (int i = 0; i < srList.size()-1; i++) {  
	            		for (int j = srList.size()-1; j > i; j--){
	            			
	            			if(srList.get(i).getSiteId()==srList.get(j).getSiteId()){
	            				srList.get(i).setNum(srList.get(i).getNum()+srList.get(j).getNum());
	            				HashSet<String> set = new HashSet<String>();
	            				String [] ids =srList.get(i).getStaffIds().split(",");
	            				for(int k=0;k<ids.length;k++){
	            					set.add(ids[k]);
	            				}
	            				 ids =srList.get(j).getStaffIds().split(",");
	            				for(int k=0;k<ids.length;k++){
	            					set.add(ids[k]);
	            				}
	            				srList.get(i).setStaffIds(set.toString().replace("[", "").replace("]", "").replace(" ", ""));
	            				srList.remove(j);
	            			}
	            		}
	                } 
				ArrayList<Site> siteList = new ArrayList<Site>();
				for(SiteRecord  sr :srList){
					Site site = sdi.getSiteById(sr.getSiteId(), con);
					if(site!=null)
						siteList.add(site);
				}
				request.getSession().setAttribute("site_list", siteList);
				request.getSession().setAttribute("sr_list", srList);
				JSONObject jo =new JSONObject();
				jo.put("site_list", siteList);
				jo.put("sr_list", srList);
				out.print(jo.toString());
			}
			else if(type.equals("1")){
				PrintWriter out = response.getWriter();
				String []staffids = request.getParameter("staffids").split(",");
				ArrayList<Staff> staffList = new ArrayList<Staff>();
				for(int i=0;i<staffids.length;i++){
					
					Staff staff = sdi1.getStaffByStaffId(Integer.parseInt(staffids[i]), con);
					if(staff!=null)
						staffList.add(staff);
				}
				request.getSession().setAttribute("staff_list", staffList);
				request.getSession().setAttribute("siteid", request.getParameter("siteid"));
				request.getSession().setAttribute("srid", request.getParameter("srid"));
				JSONObject jo =new JSONObject();
				jo.put("staff_list", staffList);
				out.print(jo.toString());
				
			}
			else if(type.equals("2")){
				ArrayList<Site> siteList = (ArrayList<Site>) request.getSession().getAttribute("site_list");
				ArrayList<SiteRecord> srList = (ArrayList<SiteRecord>) request.getSession().getAttribute("sr_list");
				// 创建柱状图  
		        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		        // 装载数据  
		        for(int i=0;i<siteList.size();i++){
		        	dataset.setValue(srList.get(i).getNum(), "人数", siteList.get(i).getName());  
		        }
		        
		          
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
		        CategoryItemRenderer customBarRenderer =  plot.getRenderer(); 
		      //设定柱子上面的颜色 
		      customBarRenderer.setSeriesPaint(0, Color.decode("#24F4DB")); // 给series1 Bar
		      customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		      customBarRenderer.setBaseItemLabelsVisible(true);
		        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));  
		        ChartUtilities.saveChartAsJPEG(new File("d:\\bar.png"), chart, 2000, 1200);
		        
		        HSSFWorkbook wb = new HSSFWorkbook();
				// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
				HSSFSheet sheet = wb.createSheet("站点信息表");
				// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
				HSSFRow row = sheet.createRow((int) 0);
				// 第四步，创建单元格，并设置值表头 设置表头居中
				HSSFCellStyle style = wb.createCellStyle();
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

				HSSFCell cell = row.createCell(0);
				cell.setCellValue("序号");
				cell.setCellStyle(style);
				cell = row.createCell(1);
				cell.setCellValue("站点名称");
				cell.setCellStyle(style);
				cell = row.createCell(2);
				cell.setCellValue("站点搭乘人数");
				cell.setCellStyle(style);
			
				
				// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
				for (int i = 0; i < siteList.size(); i++) {
					row = sheet.createRow((int) i + 1);
					Site site = (Site) siteList.get(i);
					// 第四步，创建单元格，并设置值
					row.createCell(0).setCellValue(i+1);
					row.createCell(1).setCellValue(site.getName());
					row.createCell(2).setCellValue(srList.get(i).getNum());
				}

				//根据路径下载
//				FileOutputStream fout = new FileOutputStream("E:/car_data.xls");  
//				wb.write(fout);  
//				fout.close();  
				String myexcel="site_analysis";
			    //回去输出流
			    OutputStream out1=response.getOutputStream();
			    //重置输出流
			    response.reset();
			    //设置导出Excel报表的导出形式
			    response.setContentType("application/vnd.ms-excel");
			    response.setHeader("Content-Disposition","attachment;filename="+myexcel+".xls");
			    BufferedImage bufferImg = null;  
			    ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();     
	            bufferImg = ImageIO.read(new File("d:\\bar.png"));     
	            ImageIO.write(bufferImg, "png", byteArrayOut); 
	            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();     
	            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255,(short) 5, 2, (short) 17, 24);     
	            anchor.setAnchorType(3);     
	            //插入图片    
	            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));  
			    wb.write(out1);
			   
			    out1.close();
			     
			    //设置输出形式
			    System.setOut(new PrintStream(out1));
			    //刷新输出流
			    out1.flush();
			    //关闭输出流
			    if(out1!=null){
			      out1.close();
			    }
				
			}
			else if(type.equals("3")){
				ArrayList<Staff> staffList=(ArrayList<Staff>) request.getSession().getAttribute("staff_list");
				int siteid=Integer.parseInt((String) request.getSession().getAttribute("siteid")) ;
				int srid=Integer.parseInt((String) request.getSession().getAttribute("srid")) ;
				Site site = sdi.getSiteById(siteid, con);
				SiteRecord sr = srdi.getSiteRecordById(srid, con);
				 HSSFWorkbook wb = new HSSFWorkbook();
					// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
					HSSFSheet sheet = wb.createSheet("站点信息表");
					// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
					HSSFRow row = sheet.createRow((int) 0);
					// 第四步，创建单元格，并设置值表头 设置表头居中
					HSSFCellStyle style = wb.createCellStyle();
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
					sheet.addMergedRegion(new Region(0,(short)0,0,(short)4));
					row.createCell(0).setCellValue(site.getName()+"站点 "+sr.getDate()+"乘车名单");
					
					row = sheet.createRow(1);
					HSSFCell cell = row.createCell(0);
					cell.setCellValue("序号");
					cell.setCellStyle(style);
					cell = row.createCell(1);
					cell.setCellValue("员工工号");
					cell.setCellStyle(style);
					cell = row.createCell(2);
					cell.setCellValue("员工姓名");
					cell.setCellStyle(style);
					cell = row.createCell(3);
					cell.setCellValue("员工部门");
					cell.setCellStyle(style);
					cell = row.createCell(2);
					cell.setCellValue("员工组别");
					cell.setCellStyle(style);
				
					
					// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
					for (int i = 0; i < staffList.size(); i++) {
						row = sheet.createRow((int) i + 2);
						Staff staff = (Staff) staffList.get(i);
						// 第四步，创建单元格，并设置值
						row.createCell(0).setCellValue(i+1);
						row.createCell(1).setCellValue(staff.getNumber());
						row.createCell(2).setCellValue(staff.getName());
						row.createCell(3).setCellValue(staff.getDepartment());
						row.createCell(4).setCellValue(staff.getGroup());
					}

					//根据路径下载
//					FileOutputStream fout = new FileOutputStream("E:/car_data.xls");  
//					wb.write(fout);  
//					fout.close();  
					String myexcel="Site_People";
				    //回去输出流
				    OutputStream out1=response.getOutputStream();
				    //重置输出流
				    response.reset();
				    //设置导出Excel报表的导出形式
				    response.setContentType("application/vnd.ms-excel");
				    response.setHeader("Content-Disposition","attachment;filename="+myexcel+".xls");
		
				    wb.write(out1);
				   
				    out1.close();
				     
				    //设置输出形式
				    System.setOut(new PrintStream(out1));
				    //刷新输出流
				    out1.flush();
				    //关闭输出流
				    if(out1!=null){
				      out1.close();
				    }
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
