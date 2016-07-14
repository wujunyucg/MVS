package edu.swjtu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import edu.swjtu.impl.AdminDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.Staff;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;



public class AdminDaoTest {

	@SuppressWarnings("unchecked")
	@Test
	public void test() throws ClassNotFoundException, SQLException {
		ArrayList<Staff> list = new ArrayList<Staff>();
		StaffDaoImpl sdi = new StaffDaoImpl();
		list = sdi.getStaffByPage(1,2,new DBUtil().getCon());
		System.out.println(list.get(0).getStaffId());
		System.out.println(list.get(1).getStaffId());
		list = sdi.getStaffByPage(2,2,new DBUtil().getCon());
		System.out.println(list.get(0).getStaffId());
		System.out.println(list.get(1).getStaffId());
		list = sdi.getStaffByPage(3,2,new DBUtil().getCon());
		System.out.println(list.get(0).getStaffId());
		System.out.println(list.get(1).getStaffId());
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

