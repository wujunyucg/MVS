package edu.swjtu.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import edu.swjtu.model.Admin;
import edu.swjtu.model.Staff;


public class StaffExcel {
	/**
	 * 
	 * 2016年7月16日上午11:15:49
	 * @author wujunyu
	 * TODO 导入staff excel
	 * @param c
	 * @param excelPath
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public ArrayList<Staff> inport( String excelPath)
			throws IOException, InstantiationException, IllegalAccessException {
		InputStream  fis = new FileInputStream(excelPath);
		String fileType = excelPath.substring(excelPath.lastIndexOf(".") + 1, excelPath.length());
		ArrayList<Staff> data = new ArrayList<Staff>();
		Workbook wb = null;
		if(fileType.equals("xlsx")){
			 wb = new XSSFWorkbook(fis);
		}
		else if(fileType.equals("xls")){
			 wb = new HSSFWorkbook(fis);
		}
		Sheet sht0 = wb.getSheetAt(0);
		for (Row r : sht0) {
			if (r.getRowNum() < 1) {
				continue;// 第一行是标题
			}
			Staff staff = new Staff();
			for(int i=0;i<5;i++){
				if(r.getCell(i) == null)
					return null;
				r.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
				String cellValue = r.getCell(i).getStringCellValue();
				
				switch (i){
				case 0: staff.setNumber(cellValue);break;
				case 1: staff.setName(cellValue);break;
				case 2: staff.setDepartment(cellValue);break;
				case 3: staff.setGroup(cellValue);break;
				case 4: staff.setAddress(cellValue);break;
			
				}
			}
			data.add(staff);
		}
		fis.close();
		return data;
	}
}
