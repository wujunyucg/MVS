package edu.swjtu.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.impl.ArrangeDaoImpl;
import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.LineDaoImpl;
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
	
	/**
	 * 
	 */
	public void deleteIllegalLine(ArrayList<Line> list, Connection con) throws NumberFormatException, SQLException{
		for(int i=0;i<list.size();i++){
			String[]site_ids = list.get(i).getSiteId().split(",");
			Site site = new Site();
			for(int j=0;j<site_ids.length;j++){
				site = getOneSite(Integer.valueOf(site_ids[j]).intValue(),con);
				if(site == null){
					new LineDaoImpl().deleteLine(list.get(i).getLineId(), con);
					break;
				}
			}
		}
	}
	
	/**
	 * 根据生成路线更新站点路线信息
	 * 2016年7月27日上午10:53:33
	 * @author mischief7
	 * @param list
	 * @param con
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void addLineOfSite(Connection con) throws NumberFormatException, SQLException{
		ArrayList<Line> list = new LineDaoImpl().getAllLine(con); 
		Site site = new Site();
		for(int i=0;i<list.size();i++){
			String[]site_ids = list.get(i).getSiteId().split(",");
			for(int j=0;j<site_ids.length;j++){
				site = new SiteDaoImpl().getSiteById(Integer.valueOf(site_ids[j]).intValue(), con);
				site.setLineId(site.getLineId() + list.get(i).getLineId() + ",");
				int order = j + 1;
				site.setOrder(site.getOrder() + order + ",");
				site.setLineName(site.getLineName() + list.get(i).getName() + ",");
				new SiteDaoImpl().updateSite(site, con);
			}
		}
		ArrayList<Site> sitelist = new SiteDaoImpl().getAllSite(con);
		for(int i=0;i<sitelist.size();i++){
			String temp1 = sitelist.get(i).getLineId();
			if(!temp1.isEmpty()){
				if(temp1.charAt(temp1.length()-1) == ','){
					temp1 = temp1.substring(0,temp1.length()-1);
					sitelist.get(i).setLineId(temp1);
				}
			}
			String temp2 = sitelist.get(i).getOrder();
			if(!temp2.isEmpty()){
				if(temp2.charAt(temp2.length()-1) == ','){
					temp2 = temp2.substring(0,temp2.length()-1);
					sitelist.get(i).setOrder(temp2);
				}
			}
			String temp3 = sitelist.get(i).getLineName();
			if(!temp3.isEmpty()){
				if(temp3.charAt(temp3.length()-1) == ','){
					temp3 = temp3.substring(0,temp3.length()-1);
					sitelist.get(i).setLineName(temp3);
				}
			}
			 
		}
		new SiteDaoImpl().updateListSite(sitelist, con);
	}
	
	/**
	 * 情况所有站点的路线信息
	 * 2016年7月27日上午11:13:30
	 * @author mischief7
	 * @param con
	 */
	public void deleteLineOfSite(Connection con){
		ArrayList<Site> sitelist = new SiteDaoImpl().getAllSite(con);
		for(int i=0;i<sitelist.size();i++){
			sitelist.get(i).setLineId("");
			sitelist.get(i).setOrder("");
			sitelist.get(i).setLineName("");
			new SiteDaoImpl().updateSite(sitelist.get(i), con);
		}
	}
	
}
