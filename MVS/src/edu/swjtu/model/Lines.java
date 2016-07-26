package edu.swjtu.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.impl.ArrangeDaoImpl;
import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;

public class Lines {

	public  Site getOneSite(int site_id, Connection con) throws SQLException{
		Site site= new SiteDaoImpl().getSiteById(site_id, con);
		return site;
	}
	/**
	 * 根据路线的站点id返回站点list
	 * 2016年7月24日下午4:42:55
	 * @author mischief7
	 * @param ids
	 * @return
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public ArrayList<Site> getSiteListByIds(String ids, Connection con) throws NumberFormatException, SQLException{
		String[]site_ids = ids.split(",");
		ArrayList<Site> sitelist = new ArrayList<Site>();
		for(int i=0;i<site_ids.length;i++){
			Site site = new Site();
			site = getOneSite(Integer.valueOf(site_ids[i]).intValue(),con);
			sitelist.add(site);
		}
		return sitelist;
	}
	
	public Car getOneCar(int car_id, Connection con) throws SQLException{
		Car car= new CarDaoImpl().getCarById(car_id, con);
		return car;
	}
	/**
	 * 根据路线的车辆id返回车辆list
	 * 2016年7月24日下午4:42:55
	 * @author mischief7
	 * @param ids
	 * @return
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public ArrayList<Car> getCarListByIds(String ids, Connection con) throws NumberFormatException, SQLException{
		String[]car_ids = ids.split(",");
		ArrayList<Car> carlist = new ArrayList<Car>();
		for(int i=0;i<car_ids.length;i++){
			Car car = new Car();
			car = getOneCar(Integer.valueOf(car_ids[i]).intValue(),con);
			carlist.add(car);
		}
		return carlist;
	}
	
	/**
	 * 根据站点ids获取站点名称s
	 * 2016年7月24日下午5:00:38
	 * @author mischief7
	 * @param ids
	 * @param con
	 * @return
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public String getSitesNameByIds(String ids, Connection con) throws NumberFormatException, SQLException{
		String[]site_ids = ids.split(",");
		if(site_ids.length == 0){
			return null;
		}
		else{
			String names = "";
			for(int i=0;i<site_ids.length;i++){
				Site site = new Site();
				System.out.println("****" + site_ids[i]);
				site = new SiteDaoImpl().getSiteById(Integer.valueOf(site_ids[i]).intValue(), con);
				System.out.println( site.getName()  + "||"  +  site.getLatitude() );
				names += site.getName() + "->";
			}
			names = names.substring(0,names.length()-1);
			names = names.substring(0,names.length()-1);
			return names;
		}
	}

	public String getCarNumByIds(String ids, Connection con){	
		String[]site_ids = ids.split(",");
		int len = site_ids.length;
		return "" + len;
	}
}
