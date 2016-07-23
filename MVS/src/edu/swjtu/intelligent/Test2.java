package edu.swjtu.intelligent;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.Car;
import edu.swjtu.model.Site;
import edu.swjtu.util.DBUtil;

public class Test2 {
	static DBUtil db = new DBUtil();
	UserDaoImpl udi = new UserDaoImpl();
	public static void main(String[] args) {
		Connection con = null;

		try {
			con = db.getCon();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		ArrayList<Site> sitelist = new ArrayList<Site>(); 
		sitelist = new SiteDaoImpl().getAllSite(con);
		
		ArrayList<Site> l2 = new ArrayList<Site>();
		for(int i=0;i<sitelist.size();i++){
			l2.add(new Site());
			l2.get(i).setLatitude(sitelist.get(i).getLatitude());
			l2.get(i).setLongitude(sitelist.get(i).getLongitude());
			l2.get(i).setPeoNum(sitelist.get(i).getPeoNum());
		}
		
		System.out.println(sitelist.get(0).getLatitude());
	}

}
