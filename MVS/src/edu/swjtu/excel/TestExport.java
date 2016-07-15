package edu.swjtu.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.swjtu.model.Admin;
import edu.swjtu.model.ArrCarLine;

public class TestExport {
	@Test
	public void testExport() throws IOException{
		ExportArrExcel<Admin> ex = new ExportArrExcel<Admin>();
		String[]header = {"id","权限ids","Name"};
		List<Admin> data = new ArrayList<Admin>();
		Admin ad = new Admin();
		ad.setAdminId(1);
		ad.setName("jimo");
		ad.setPowerId("1,2,3");
		data.add(ad);
		data.add(ad);
		data.add(ad);
		data.add(ad);
		data.add(ad);
		OutputStream out = new FileOutputStream("D://a.xls");
		ex.export("exadmin", header, data, out);
		out.flush();
		out.close();
	}
	@Test
	public void testArrCarLineEx() throws IOException{
		ExportArrExcel<ArrCarLine> ex = new ExportArrExcel<ArrCarLine>();
		String[]header = {"arrid","班次名称","日期","发车时间","线路名称","车牌号","司机"};
		List<ArrCarLine> data = new ArrayList<ArrCarLine>();
		ArrCarLine acl = new ArrCarLine();
		acl.setArrangeId(1);
		acl.setArrName("班次1");
		acl.setDate("2016-07-11");
		acl.setDriver("jimo");
		acl.setLicensePlate("1234567");
		acl.setLineName("线路1");
		acl.setTime("6:00");
		data.add(acl);
		data.add(acl);
		data.add(acl);
		data.add(acl);
		data.add(acl);
		data.add(acl);
		OutputStream out = new FileOutputStream("D://b.xls");
		ex.export("exadmin", header, data, out);
		out.flush();
		out.close();
	}
	@Test
	public void testInport() throws InstantiationException, IllegalAccessException, IOException{
		ExportArrExcel<Admin> ex = new ExportArrExcel<Admin>();
		List<Admin> d = ex.inport(Admin.class, "D://a.xls");
		System.out.println(d.size()+" name="+d.get(4).getName());
	}
}
