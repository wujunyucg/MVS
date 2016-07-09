package edu.swjtu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	/*private String url = "jdbc:mysql://119.29.143.245:3306/car_factory?useSSL=false";
	private String username = "root";
	private String password = "mysqlroot";
	private String jdbcName = "com.mysql.jdbc.Driver";*/
	private String url = "jdbc:mysql://localhost:3306/car_factory?useSSL=false";
	private String username = "root";
	private String password = "root";
	private String jdbcName = "com.mysql.jdbc.Driver";
	/**
	 * 2016.6.5 2:04:46
	 * @author jimolonely
	 * 数据库连接
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getCon() throws ClassNotFoundException, SQLException{
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}
	
	/**
	 * 关闭连接
	 * 2016��6��5������2:06:39
	 * @author jimolonely
	 * @param con
	 * @throws SQLException
	 */
	public void closeCon(Connection con) throws SQLException{
		if(null!=con){
			con.close();
		}
	}	
}
