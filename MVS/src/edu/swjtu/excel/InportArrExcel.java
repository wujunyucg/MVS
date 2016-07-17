package edu.swjtu.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.swjtu.model.ArrCarLine;

public class InportArrExcel {
	/**
	 * 导入班次excel
	 * 2016年7月16日下午7:24:58
	 * @author jimolonely
	 * @param excelPath
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public ArrayList<ArrCarLine> inport( String excelPath)
			throws IOException, InstantiationException, IllegalAccessException {
		InputStream  fis = new FileInputStream(excelPath);
		String fileType = excelPath.substring(excelPath.lastIndexOf(".") + 1, excelPath.length());
		ArrayList<ArrCarLine> data = new ArrayList<ArrCarLine>();
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
			ArrCarLine acl = new ArrCarLine();
			for(int i=0;i<7;i++){
				if(r.getCell(i) == null)
					return null;
				r.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
				String cellValue = r.getCell(i).getStringCellValue();
				
				switch (i){
				case 0: acl.setArrangeId(Integer.parseInt(cellValue));break;
				case 1: acl.setArrName(cellValue);break;
				case 2: acl.setDate(cellValue);break;
				case 3: acl.setTime(cellValue);break;
				case 4: acl.setLineName(cellValue);break;
				case 5: acl.setLicensePlate(cellValue);break;
				case 6: acl.setDriver(cellValue);break;
				}
			}
			data.add(acl);
		}
		fis.close();
		return data;
	}
}
