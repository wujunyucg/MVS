package edu.swjtu.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;

/**
 * ������
 * 2016��6��7������6:55:52
 * @author mischief7
 * TODO
 */
public class UserDaoTest {
	
	@Test
	public void testLogin() throws ClassNotFoundException, SQLException{
		User user = new User();
		user.setAdminId(1);
		user.setNumber("1");
		user.setPassword("1");
		user.setType(2);
		
		User u = new UserDaoImpl().login(user, new DBUtil().getCon());
		if(u!=null){
			System.out.println("yes");
		}
	}
	
	@Test
	public void testAddUser() throws ClassNotFoundException, SQLException{
		User user = new User();
		user.setAdminId(1);
		user.setNumber("555");
		user.setPassword("555");
		user.setType(1);
		
		int u = new UserDaoImpl().addUser(user, new DBUtil().getCon());
		System.out.println(u);//1
	}
	
	@Test
	public void testDeleteUser() throws ClassNotFoundException, SQLException{
		UserDaoImpl testdelete = new UserDaoImpl();
		testdelete.deleteUser(3, new DBUtil().getCon());
		System.out.println("111");
	}
	
	@Test
	public void testUpdataUser() throws ClassNotFoundException, SQLException{
		User use = new User();
		UserDaoImpl userDao = new UserDaoImpl();
		
		use.setUserId(4);
		use.setNumber("wang1111");
		use.setPassword("189");
		use.setAdminId(12);
		use.setType(1);
		
		int u = new UserDaoImpl().updateUser(use, new DBUtil().getCon());
		System.out.println(u);//1
	}
	
	@Test
	public void testFindAllUser() throws ClassNotFoundException, SQLException{
		 ArrayList<User> list = new UserDaoImpl().getAllUser(new DBUtil().getCon());
		 System.out.println(list.get(0).getNumber());
		 System.out.println(list.get(1).getNumber());
	}
	
	@Test
	public void testFindUser() throws ClassNotFoundException, SQLException{
		User use = new UserDaoImpl().getUserById(11, new DBUtil().getCon());
		 System.out.println(use.getNumber());
	}
	
	@Test
	public void testFindUserByNumber() throws ClassNotFoundException, SQLException{
		User use = new UserDaoImpl().getUserByNumber("wang1111", new DBUtil().getCon());
		System.out.println(use.getPassword());
		System.out.println("123");
	}
	/**
	 * 2016年7月11日上午8:57:15
	 * @author jimolonely
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testDeleteUserByAdminId() throws ClassNotFoundException, SQLException{
		int re = new UserDaoImpl().deleteUserByAdminId(1,new DBUtil().getCon());
		System.out.println(re);
	}
}
