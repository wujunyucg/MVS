package edu.swjtu.dao;

import java.sql.SQLException;

import org.junit.Test;

import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;

/**
 * 测试类
 * 2016年6月7日下午6:55:52
 * @author jimolonely
 * TODO
 */
public class UserDaoTest {
	
	@Test
	public void testLogin() throws ClassNotFoundException, SQLException{
		User user = new User();
		user.setAdminId(1);
		user.setNumber("1");
		user.setPassword("1");
		
		User u = new UserDaoImpl().login(user, new DBUtil().getCon());
		if(u!=null){
			System.out.println("yes");
		}
	}
	
	@Test
	public void testAddUser() throws ClassNotFoundException, SQLException{
		User user = new User();
		user.setAdminId(1);
		user.setNumber("1");
		user.setPassword("1");
		
		int u = new UserDaoImpl().addUser(user, new DBUtil().getCon());
		System.out.println(u);//1
	}
}
