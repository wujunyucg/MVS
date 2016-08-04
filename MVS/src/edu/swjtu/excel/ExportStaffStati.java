package edu.swjtu.excel;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.util.Region;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.CategoryDataset;

import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.impl.LineRecordDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Line;
import edu.swjtu.model.LineRecord;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;

public class ExportStaffStati extends HttpServlet {
	DBUtil db = new DBUtil();
	
	public ExportStaffStati() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");  
		Connection con = null;
		try {
			con = db.getCon();
			String type = request.getParameter("type");
				if(type.equals("1")){	//员工信息导出
					String linerecId = request.getParameter("linerecId");
					LineRecord lr = null;
					StaffDaoImpl sdi = new StaffDaoImpl();
					lr = new LineRecordDaoImpl().getLineRecordById(Integer.valueOf(linerecId).intValue(), con);
					String[] staff_ids = lr.getStaffIds().split(",");
					ArrayList<Staff> staffList = new ArrayList<Staff>();
					for(int i=0;i<staff_ids.length;i++){
						staffList.add(sdi.getStaffByStaffId(Integer.valueOf(staff_ids[i]).intValue(), con));
					}
					Line line = null;
					line = new LineDaoImpl().getLineById(con, lr.getLineId());
					 HSSFWorkbook wb = new HSSFWorkbook();
						// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
						HSSFSheet sheet = wb.createSheet("线路信息表");
						// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
						HSSFRow row = sheet.createRow((int) 0);
						// 第四步，创建单元格，并设置值表头 设置表头居中
						HSSFCellStyle style = wb.createCellStyle();
						style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
						sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 7));
						row.createCell(0).setCellValue(line.getName()+"-线路 "+lr.getDate()+"-乘车名单");
						
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
						cell = row.createCell(4);
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
		
						String myexcel="line_People";
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
				}else if(type.equals("2")){
					String linenames = request.getParameter("linenames");
					String linerates = request.getParameter("linerates");
					String[] line_names = linenames.split(",");
					String[] line_rates = linerates.split(",");

					// 创建柱状图  
			        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
			        // 装载数据  
			        for(int i=0;i<line_names.length;i++){
			        	dataset.setValue(Double.parseDouble(line_rates[i]), "乘坐率", line_names[i]);  
			        }
			          
			        // 3D柱状图  
			        JFreeChart chart = ChartFactory.createBarChart("线路统计报表", "线路", "乘坐率",  
			                dataset, PlotOrientation.VERTICAL, true, true, false);  
			        // 解决中文乱码  
			        CategoryPlot plot = chart.getCategoryPlot();  
			        CategoryAxis domainAxis = plot.getDomainAxis();  
			        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();  
			  
			        TextTitle textTitle = chart.getTitle();  
			        textTitle.setFont(new Font("黑体", Font.PLAIN, 50));  
			        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 35));  
			        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 35));  
			        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			        numberAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 35));  
			        numberAxis.setLabelFont(new Font("宋体", Font.PLAIN, 35)); 
			        CategoryItemRenderer customBarRenderer =  plot.getRenderer(); 
			      //设定柱子上面的颜色 
			      customBarRenderer.setSeriesPaint(0, Color.decode("#9FB6CD")); // 给series1 Bar
			      customBarRenderer.setItemLabelFont(new Font("sans-serif", Font.PLAIN, 20));
			      customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			      customBarRenderer.setBaseItemLabelsVisible(true);
			        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 35));  
			        ChartUtilities.saveChartAsJPEG(new File("d:\\bar.png"), chart, 2000, 1200);
			        
			        HSSFWorkbook wb = new HSSFWorkbook();
					// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
					HSSFSheet sheet = wb.createSheet("线路信息表");
					// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
					HSSFRow row = sheet.createRow((int) 0);
					// 第四步，创建单元格，并设置值表头 设置表头居中
					HSSFCellStyle style = wb.createCellStyle();
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

					HSSFCell cell = row.createCell(0);
					cell.setCellValue("序号");
					cell.setCellStyle(style);
					cell = row.createCell(1);
					cell.setCellValue("线路名称");
					cell.setCellStyle(style);
					cell = row.createCell(2);
					cell.setCellValue("乘坐率");
					cell.setCellStyle(style);
				
					
					// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
					for (int i = 0; i < line_names.length; i++) {
						row = sheet.createRow((int) i + 1);
						// 第四步，创建单元格，并设置值
						row.createCell(0).setCellValue(i+1);
						row.createCell(1).setCellValue(line_names[i]);
						row.createCell(2).setCellValue(line_rates[i]);
					}

					String myexcel="line_analysis";
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
		            anchor.setAnchorType(ClientAnchor.MOVE_AND_RESIZE);     
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
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			} catch (Exception e) {
			// TODO Auto-generated catch block
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

	public void init() throws ServletException {
	}

}
