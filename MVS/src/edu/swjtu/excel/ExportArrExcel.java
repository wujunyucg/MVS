package edu.swjtu.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.junit.Test;

import edu.swjtu.model.Admin;

public class ExportArrExcel<T> {
	/**
	 * 导出excel的模板类
	 * 2016年7月15日上午10:46:05
	 * @author jimolonely
	 * @param title 表格名称
	 * @param headers 导出的excel的列名
	 * @param dataset 数据集合
	 * @param out 输出流
	 */
	public void export(String title,String[]headers,
			Collection<T>dataset,OutputStream out){
		//生产一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		//一个表格
		HSSFSheet sheet = wb.createSheet(title);
		//设置默认宽度，高度
		sheet.setDefaultColumnWidth(15);
		sheet.setDefaultRowHeightInPoints(20);
		//样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		
		//字体
		HSSFFont font = wb.createFont();
		font.setColor(HSSFColor.BLACK.index);
		
		//应用字体到当前样式
		style.setFont(font);
		
		//产生表格标题
		HSSFRow row = sheet.createRow(0);
		for(int i=0;i<headers.length;i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		
		//遍历集合，产生数据
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while(it.hasNext()){
			index++;
			row = sheet.createRow(index);
			T t = (T)it.next();
			//利用反射，得到属性值
			Field[]fields = t.getClass().getDeclaredFields();
			for(int i=0;i<fields.length;i++){
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				Field field = fields[i];
				String fName = field.getName();
				String getMethodName = "get"+
						fName.substring(0,1).toUpperCase()
						+fName.substring(1);
				try{
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(t, new Object[]{});
					String textValue = value.toString();
					if(null!=textValue){
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");   
                        Matcher matcher = p.matcher(textValue);
                        if(matcher.matches()){
                        	//是数字
                        	cell.setCellValue(Double.parseDouble(textValue));
                        }else{
                        	HSSFRichTextString rs = new HSSFRichTextString(textValue);
                        	HSSFFont font3 = wb.createFont();
                        	font3.setColor(HSSFColor.BLACK.index);
                        	rs.applyFont(font3);
                        	cell.setCellValue(rs);
                        }
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		try {
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
