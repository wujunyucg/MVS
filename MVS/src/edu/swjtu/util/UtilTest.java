package edu.swjtu.util;

import java.sql.SQLException;

import org.junit.Test;

/**
 * 对util包下的方法进行JUnit测试
 * 2016年6月3日下午12:50:54
 * @author jimolonely
 * TODO
 */
public class UtilTest {
     
	/**
	 * 测试数据库连接
	 * 2016年6月5日下午2:13:26
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
