package edu.swjtu.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.model.Car;
import edu.swjtu.model.Site;
import edu.swjtu.util.DBUtil;

/**
 * 
 * ExportSiteServlet.java类
 * 2016年7月24日
 * @author wujunyu
 * TODo
 */
public class ExportSiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExportSiteServlet() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 第一步，创建一个webbook，对应一个Excel文件
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
				cell.setCellValue("站点地址");
				cell.setCellStyle(style);
				cell = row.createCell(3);
				cell.setCellValue("站点人数");
				cell.setCellStyle(style);
				cell = row.createCell(4);
				cell.setCellValue("站点所属线路");
				cell.setCellStyle(style);
				cell = row.createCell(5);
				cell.setCellValue("站点经度");
				cell.setCellStyle(style);
				cell = row.createCell(6);
				cell.setCellValue("站点纬度");
				cell.setCellStyle(style);
				cell = row.createCell(7);
				cell.setCellValue("站点顺序");
				cell.setCellStyle(style);
				
				// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
				Connection con = null;
				DBUtil db = new DBUtil();
				try {
					con = db.getCon();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				
				ArrayList<Site> siteList = null;
				siteList = new SiteDaoImpl().getAllSite(con);

				for (int i = 0; i < siteList.size(); i++) {
					row = sheet.createRow((int) i + 1);
					Site site = (Site) siteList.get(i);
					// 第四步，创建单元格，并设置值
					row.createCell(0).setCellValue(i+1);
					row.createCell(1).setCellValue(site.getName());
					row.createCell(2).setCellValue(site.getAddress());
					row.createCell(3).setCellValue(site.getPeoNum());
					row.createCell(4).setCellValue(site.getLineId());
					row.createCell(5).setCellValue(site.getLatitude());
					row.createCell(6).setCellValue(site.getLongitude());
					row.createCell(7).setCellValue(site.getOrder());
				}

				//根据路径下载
//				FileOutputStream fout = new FileOutputStream("E:/car_data.xls");  
//				wb.write(fout);  
//				fout.close();  
			
				String myexcel="All_Site";
			    //回去输出流
			    OutputStream out=response.getOutputStream();
			    //重置输出流
			    response.reset();
			    //设置导出Excel报表的导出形式
			    response.setContentType("application/vnd.ms-excel");
			    response.setHeader("Content-Disposition","attachment;filename="+myexcel+".xls");
			    
			    wb.write(out);
			    out.close();
			     
			    //设置输出形式
			    System.setOut(new PrintStream(out));
			    //刷新输出流
			    out.flush();
			    //关闭输出流
			    if(out!=null){
			      out.close();
			    }
			    
			    try {
					db.closeCon(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
