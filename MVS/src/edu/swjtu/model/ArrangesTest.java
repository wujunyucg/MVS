package edu.swjtu.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.intelligent.PlanRoute;
import edu.swjtu.model.Arranges;
import edu.swjtu.util.DBUtil;

public class ArrangesTest {

	
	DBUtil db = new DBUtil();
	UserDaoImpl udi = new UserDaoImpl();
	
	
	@Test
	public void testArr() throws SQLException{
		
		Connection con = null;

		try {
			con = db.getCon();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(new Arranges().getAllArrangeName("-1",con));
		
		try {
			db.closeCon(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
}
