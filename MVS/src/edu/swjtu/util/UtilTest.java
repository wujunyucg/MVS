package edu.swjtu.util;

import java.sql.SQLException;

import org.junit.Test;

import edu.swjtu.util.DBUtil;

/**
 * 数据库
 * 2016.6.3 12:50:54
 * @author jimolonely
 * TODO
 */
public class UtilTest {
     
	/**
	 * 连接
	 * 2016 6 5 2:13:26
	 * @author jimolonely
	 */
	@Test
	public void testCon(){
		DBUtil db = new DBUtil();
		try {
			db.getCon();
			System.out.println("yes");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
