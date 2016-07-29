package edu.swjtu.intelligent;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.junit.Test;

import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.Car;
import edu.swjtu.model.Line;
import edu.swjtu.model.Lines;
import edu.swjtu.model.Site;
import edu.swjtu.util.DBUtil;


public class RouteTest {
	DBUtil db = new DBUtil();
	UserDaoImpl udi = new UserDaoImpl();
	
	
	@Test
	public void testPlanRoute(){
		
		Connection con = null;

		try {
			con = db.getCon();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		ArrayList<Site> sitelist = new ArrayList<Site>(); 
		sitelist = new SiteDaoImpl().getAllSite(con);
		ArrayList<Car> carlist = new ArrayList<Car>(); 
		carlist = new CarDaoImpl().getAllCar(con);
		
		Site fac_site = new Site();
		fac_site.setAddress("1");
		fac_site.setDelay(1);

		fac_site.setLatitude(30.655826);
		fac_site.setLineId(1);
		fac_site.setLongitude(104.065349);
		fac_site.setName("1");
		fac_site.setOrder(1);
		fac_site.setPeoNum(0);
		fac_site.setSiteId(99);
		
		
		System.out.println((3+0.0)/4*0.1);
		ArrayList<Line> linelist = new ArrayList<Line>();
		PlanRoute test = new PlanRoute();
		//linelist = test.intelligentLine(0.9, -1.0, sitelist,carlist, fac_site, 0, 0);
		System.out.println(linelist);
		for(int i=0;i<linelist.size();i++){
			System.out.println("人数：" + linelist.get(i).getNum());
		}
		
		try {
			db.closeCon(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
//	@Test
//	public void testSort(){
//		Site s = new Site();
//		s.setLongitude("5");
//		s.setLatitude("5");
//		Site s1 = new Site();
//		s1.setLongitude("2");
//		s1.setLatitude("1");
//		Site s2 = new Site();
//		s2.setLongitude("-2");
//		s2.setLatitude("1");
//		Site s3 = new Site();
//		s3.setLongitude("0");
//		s3.setLatitude("2");
//		Site s4 = new Site();
//		s4.setLongitude("2");
//		s4.setLatitude("-1");
//		Site s5 = new Site();
//		s5.setLongitude("1");
//		s5.setLatitude("-2");
//		ArrayList<Site> list = new ArrayList<Site>();
//		list.add(s1);
//		list.add(s2);
//		list.add(s3);
//		list.add(s4);
//		list.add(s5);
//		
//		ArrayList<Site> list2 = new PlanRoute().getSortedSites(3, list, s);
//		
//		for(Site ss:list2){
//			System.out.println("x:"+ss.getLongitude()+" y:"+ss.getLatitude());
//		}
//	}
//	@Test
//	public void test1(){
//		Connection con = null;
//
//		try {
//			con = db.getCon();
//		} catch (ClassNotFoundException | SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		
//		ArrayList<Site> sitelist = new ArrayList<Site>(); 
//		sitelist = new SiteDaoImpl().getAllSite(con);
//		ArrayList<Car> carlist = new ArrayList<Car>(); 
//		carlist = new CarDaoImpl().getAllCar(con);
//		
//		Site fac_site = new Site();
//		fac_site.setAddress("1");
//		fac_site.setDelay(1);
//		fac_site.setLatitude("30.581111888888888");
//		fac_site.setLineId(1);
//		fac_site.setLongitude("104.08378722222223");
//		fac_site.setName("1");
//		fac_site.setOrder(1);
//		fac_site.setPeoNum(0);
//		fac_site.setSiteId(99);
//		
//		DecimalFormat df = new DecimalFormat( "0.000000 ");
//		ArrayList<Site> list2 = new PlanRoute().getSortedSites(85, sitelist, fac_site);
//		
//		for(int j=0;j<list2.size();j++){
//		
//			System.out.println("{lng:[["+df.format(Double.parseDouble(fac_site.getLongitude())) +"," + df.format(Double.parseDouble(fac_site.getLatitude()))+"],");
//			System.out.println("["+df.format(Double.parseDouble(list2.get(j).getLongitude())) + ","+ df.format(Double.parseDouble(list2.get(j).getLatitude()))+"]]"+"},");
//		
//			System.out.println("");
//		}
//		//System.out.println("");
//		
//	}
//	
//	@Test
//	public void testQuick(){
//		Site s = new Site();
//		s.setLongitude("0");
//		s.setLatitude("0");
//		Site s1 = new Site();
//		s1.setLongitude("2");
//		s1.setLatitude("1");
//		Site s2 = new Site();
//		s2.setLongitude("-2");
//		s2.setLatitude("1");
//		Site s3 = new Site();
//		s3.setLongitude("0");
//		s3.setLatitude("2");
//		Site s4 = new Site();
//		s4.setLongitude("2");
//		s4.setLatitude("-1");
//		Site s5 = new Site();
//		s5.setLongitude("1");
//		s5.setLatitude("-2");
//		ArrayList<Site> list = new ArrayList<Site>();
//		list.add(s1);
//		list.add(s2);
//		list.add(s3);
//		list.add(s4);
//		list.add(s5);
//		
//		new PlanRoute().quicksort(list, s, 0, list.size()-1);;
//		
//		for(Site ss:list){
//			System.out.println("x:"+ss.getLongitude()+" y:"+ss.getLatitude());
//		}
//	}
//	
//	@Test
//	public void testGre(){
//		Site s = new Site();
//		s.setLongitude("0");
//		s.setLatitude("0");
//		Site s1 = new Site();
//		s1.setLongitude("1.1");
//		s1.setLatitude("1");
//		Site s2 = new Site();
//		s2.setLongitude("-1");
//		s2.setLatitude("1");
//		Site s3 = new Site();
//		s3.setLongitude("0");
//		s3.setLatitude("-2");
//		Site s4 = new Site();
//		s4.setLongitude("-3");
//		s4.setLatitude("3");
//		Site s5 = new Site();
//		s5.setLongitude("1");
//		s5.setLatitude("-4");
//		ArrayList<Site> list = new ArrayList<Site>();
//		list.add(s1);
//		list.add(s2);
//		list.add(s3);
//		list.add(s4);
//		list.add(s5);
//		
//		double distance = new PlanRoute().greAlgorithm(0,4,list,s);
//		System.out.println(distance);
//		
//		//System.out.println( new PlanRoute().get_greline(0,4,list,s));
//	}
//	
//	
//	@Test
//	public void testShortPath(){
//		Site s = new Site();
////		s.setLongitude("0");
////		s.setLatitude("0");
////		Site s1 = new Site();
////		s1.setLongitude("1.1");
////		s1.setLatitude("1");
////		Site s2 = new Site();
////		s2.setLongitude("-1");
////		s2.setLatitude("1");
////		Site s3 = new Site();
////		s3.setLongitude("0");
////		s3.setLatitude("-2");
////		Site s4 = new Site();
////		s4.setLongitude("-3");
////		s4.setLatitude("3");
////		Site s5 = new Site();
////		s5.setLongitude("1");
////		s5.setLatitude("-4");
//		s.setLongitude("0");
//		s.setLatitude("0");
//		Site s1 = new Site();
//		s1.setLongitude("1.1");
//		s1.setLatitude("1");
//		Site s2 = new Site();
//		s2.setLongitude("-1");
//		s2.setLatitude("1");
//		Site s3 = new Site();
//		s3.setLongitude("-2");
//		s3.setLatitude("2");
//		Site s4 = new Site();
//		s4.setLongitude("-3");
//		s4.setLatitude("3");
//		Site s5 = new Site();
//		s5.setLongitude("-4");
//		s5.setLatitude("4");
//		ArrayList<Site> list = new ArrayList<Site>();
//		list.add(s1);
//		list.add(s2);
//		list.add(s3);
//		list.add(s4);
//		list.add(s5);
//		
//		double distance = new PlanRoute().cal_distance(0,4,list,s);
//		System.out.println(distance);
//	}
//	
//	@Test
//	public void testGetArc(){
//		Site s = new Site();
//		s.setLongitude("0");
//		s.setLatitude("0");
//		Site s1 = new Site();
//		s1.setLongitude("1");
//		s1.setLatitude("1.7");
//		Site s2 = new Site();
//		s2.setLongitude("1.7");
//		s2.setLatitude("1");
//		System.out.println(new PlanRoute().getArc(s1, s2, s));
//	}
	
	@Test
	public void testModifyLine() throws ClassNotFoundException, SQLException{
		Line line = new Line();
		line.setLineId(855);
		line.setCarId("1");
		line.setName("xian");
		line.setRate(0);
		line.setSiteId("669,695,0");
		
		Connection con = new DBUtil().getCon();
		new Lines().modifyLineNum(line, con);
	}
	
	@Test
	public void testDeleteOneLine() throws ClassNotFoundException, SQLException{
		Line line = new Line();
		line.setLineId(857);
		line.setCarId("1");
		line.setName("xian");
		line.setRate(0);
		line.setSiteId("630,0");
		
		Connection con = new DBUtil().getCon();
		new Lines().deleteOneLine(line, con);
	}
}
