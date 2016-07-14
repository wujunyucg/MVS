package edu.swjtu.dao;

import java.sql.SQLException;

import org.junit.Test;

import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.util.DBUtil;

public class LineDaoTest {
	@Test
	public void testGetById() throws ClassNotFoundException, SQLException{
		System.out.println(new LineDaoImpl().getLineById(new DBUtil().getCon(), 1).getName());
	}
}
