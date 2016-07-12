package edu.swjtu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;



public class AdminDaoTest {

	@SuppressWarnings("unchecked")
	@Test
	public void test() throws ClassNotFoundException, SQLException {
		
	
		ArrayList<User> userList = new ArrayList<User>();
		UserDaoImpl udi = new UserDaoImpl();
		userList =	udi.getAllUser(new DBUtil().getCon());
		//System.out.println(userList.get(0).getNumber());
		JSONObject jsonObject = new JSONObject();  
	       jsonObject.put("user", userList);  
	      System.out.println(jsonObject); 
	      /* JSONArray jsonArray = new JSONArray();  
	        jsonArray.add(jsonObject);  
	        System.out.println(jsonObject); */
		}
	/**
	 * 测试
	 * 2016年7月10日上午11:36:19
	 * @author jimolonely
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	/*public void jimoTest() throws ClassNotFoundException, SQLException{
		String name = "1";
		System.out.println(new AdminDaoImpl().getAdminByName(name, new DBUtil().getCon()));
	}
		*/
}

