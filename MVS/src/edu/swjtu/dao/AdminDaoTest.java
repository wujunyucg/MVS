package edu.swjtu.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.swjtu.impl.AdminDaoImpl;
import edu.swjtu.model.Admin;
import edu.swjtu.util.DBUtil;

public class AdminDaoTest {

	@Test
	public void test() throws ClassNotFoundException, SQLException {
		AdminDaoImpl adi = new AdminDaoImpl();
		/*List<Admin> adminList = new ArrayList<Admin>();
		adminList = adi.getAllAdmin(new DBUtil().getCon());
		for(int i=0;i<adminList.size();i++)
			System.out.println(adminList.get(i).getName());*/
		Admin admin =adi.getAdminById(1,new DBUtil().getCon());
		admin.setName("µÛ¹úÊ±´ú");
		adi.updateAdmin(admin , new DBUtil().getCon());
	}

}
