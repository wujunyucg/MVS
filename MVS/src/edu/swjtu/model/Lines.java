package edu.swjtu.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import edu.swjtu.impl.ArrangeDaoImpl;
import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.intelligent.SearchRoute;

public class Lines {
	
	/**
	 * 根据两点经纬度计算距离
	 */
	public static double Distance(double long1, double lat1, double long2,
			double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2
				* R
				* Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
						* Math.cos(lat2) * sb2 * sb2));
		return d;
	}
	
	
	public double lineLength(int lineId, Connection con) throws SQLException{
		Line line = null;
		line = new LineDaoImpl().getLineById(con, lineId);
		SiteDaoImpl sdi = new SiteDaoImpl(); 
		String[] site_ids = line.getSiteId().split(",");
		double all_len = 0.0;
		Site s1 = null, s2 = null;
		
		for(int i=0;i<site_ids.length-1;i++){
			s1 = sdi.getSiteById(Integer.valueOf(site_ids[i]).intValue(), con);
			s2 = sdi.getSiteById(Integer.valueOf(site_ids[i+1]).intValue(), con);
			if(s1 != null&&s2 != null){
				all_len += Distance(s1.getLongitude(),s1.getLatitude(),s2.getLongitude(),s2.getLatitude());
			}
		}
		return all_len;
	}
	
	
	
	/**
	 * 优先队列比较
	 */
	public Comparator<LineRecord> OrderIsdn1 = new Comparator<LineRecord>() {
		@Override
		public int compare(LineRecord o1, LineRecord o2) {
			if (o1.getRate() < o2.getRate()) {
				return -1;
			} else if (o1.getRate() > o2.getRate()) {
				return 1;
			} else {
				return 0;
			}
		}
	};
	
	public Comparator<LineRecord> OrderIsdn2 = new Comparator<LineRecord>() {
		@Override
		public int compare(LineRecord o1, LineRecord o2) {
			if (Double.parseDouble(o1.getStaffIds()) > Double.parseDouble(o2.getStaffIds())) {
				return -1;
			} else if (Double.parseDouble(o1.getStaffIds()) < Double.parseDouble(o2.getStaffIds())) {
				return 1;
			} else {
				return 0;
			}
		}
	};
	
	public Site getOneSite(int site_id, Connection con) throws SQLException {
		Site site = new SiteDaoImpl().getSiteById(site_id, con);
		return site;
	}

	/**
	 * 根据路线的站点id返回站点list 2016年7月24日下午4:42:55
	 * 
	 * @author mischief7
	 * @param ids
	 * @return
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public ArrayList<Site> getSiteListByIds(String ids, Connection con)
			throws NumberFormatException, SQLException {
		String[] site_ids = ids.split(",");
		ArrayList<Site> sitelist = new ArrayList<Site>();
		for (int i = 0; i < site_ids.length; i++) {
			Site site = new Site();
			site = getOneSite(Integer.valueOf(site_ids[i]).intValue(), con);
			sitelist.add(site);
		}
		return sitelist;
	}

	public Car getOneCar(int car_id, Connection con) throws SQLException {
		Car car = new CarDaoImpl().getCarById(car_id, con);
		return car;
	}

	/**
	 * 根据路线的车辆id返回车辆list 2016年7月24日下午4:42:55
	 * 
	 * @author mischief7
	 * @param ids
	 * @return
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public ArrayList<Car> getCarListByIds(String ids, Connection con)
			throws NumberFormatException, SQLException {
		String[] car_ids = ids.split(",");
		ArrayList<Car> carlist = new ArrayList<Car>();
		for (int i = 0; i < car_ids.length; i++) {
			Car car = new Car();
			car = getOneCar(Integer.valueOf(car_ids[i]).intValue(), con);
			carlist.add(car);
		}
		return carlist;
	}

	/**
	 * 根据站点ids获取站点名称s 2016年7月24日下午5:00:38
	 * 
	 * @author mischief7
	 * @param ids
	 * @param con
	 * @return
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public String getSitesNameByIds(String ids, Connection con)
			throws NumberFormatException, SQLException {
		String[] site_ids = ids.split(",");
		if (site_ids.length == 0) {
			return null;
		} else {
			String names = "";
			for (int i = 0; i < site_ids.length; i++) {
				Site site = new Site();
				site = new SiteDaoImpl().getSiteById(
						Integer.valueOf(site_ids[i]).intValue(), con);
				names += site.getName() + "-";
			}
			names = names.substring(0, names.length() - 1);
			return names;
		}
	}

	public String getCarNumByIds(String ids, Connection con) {
		String[] site_ids = ids.split(",");
		int len = site_ids.length;
		return "" + len;
	}

	/**
	 * 删除不存在站点相关的线路 2016年7月27日下午2:28:25
	 * 
	 * @author mischief7
	 * @param list
	 * @param con
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void deleteIllegalLine(ArrayList<Line> list, Connection con)
			throws NumberFormatException, SQLException {
		for (int i = 0; i < list.size(); i++) {
			String[] site_ids = list.get(i).getSiteId().split(",");
			Site site = new Site();
			for (int j = 0; j < site_ids.length; j++) {
				site = getOneSite(Integer.valueOf(site_ids[j]).intValue(), con);
				if (site == null) {
					new LineDaoImpl().deleteLine(list.get(i).getLineId(), con);
					break;
				}
			}
		}
	}

	/**
	 * 根据生成路线更新站点路线信息 2016年7月27日上午10:53:33
	 * 
	 * @author mischief7
	 * @param list
	 * @param con
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public void addLineOfSite(Connection con) throws NumberFormatException,
			SQLException {
		ArrayList<Line> list = new LineDaoImpl().getAllLine(con);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setName("智能路线" + list.get(i).getLineId() + "_Z");
			new LineDaoImpl().updateLine(con, list.get(i));
		}
		Site site = new Site();
		for (int i = 0; i < list.size(); i++) {
			String[] site_ids = list.get(i).getSiteId().split(",");
			for (int j = 0; j < site_ids.length; j++) {
				site = new SiteDaoImpl().getSiteById(
						Integer.valueOf(site_ids[j]).intValue(), con);
				site.setLineId(site.getLineId() + list.get(i).getLineId() + ",");
				int order = j + 1;
				site.setOrder(site.getOrder() + order + ",");
				site.setLineName(site.getLineName() + list.get(i).getName()
						+ ",");
				new SiteDaoImpl().updateSite(site, con);
			}
		}
		ArrayList<Site> sitelist = new SiteDaoImpl().getAllSite(con);
		for (int i = 0; i < sitelist.size(); i++) {
			String temp1 = sitelist.get(i).getLineId();
			if (temp1 != null && !temp1.isEmpty()) {
				if (temp1.charAt(temp1.length() - 1) == ',') {
					temp1 = temp1.substring(0, temp1.length() - 1);
					sitelist.get(i).setLineId(temp1);
				}
			}
			String temp2 = sitelist.get(i).getOrder();
			if (temp2 != null && !temp2.isEmpty()) {
				if (temp2.charAt(temp2.length() - 1) == ',') {
					temp2 = temp2.substring(0, temp2.length() - 1);
					sitelist.get(i).setOrder(temp2);
				}
			}
			String temp3 = sitelist.get(i).getLineName();
			if (temp3 != null && !temp3.isEmpty()) {
				if (temp3.charAt(temp3.length() - 1) == ',') {
					temp3 = temp3.substring(0, temp3.length() - 1);
					sitelist.get(i).setLineName(temp3);
				}
			}

		}
		new SiteDaoImpl().updateListSite(sitelist, con);
	}

	/**
	 * 情况所有站点的路线信息 2016年7月27日上午11:13:30
	 * 
	 * @author mischief7
	 * @param con
	 */
	public void deleteLineOfSite(Connection con) {
		ArrayList<Site> sitelist = new SiteDaoImpl().getAllSite(con);
		for (int i = 0; i < sitelist.size(); i++) {
			sitelist.get(i).setLineId("");
			sitelist.get(i).setOrder("");
			sitelist.get(i).setLineName("");
			new SiteDaoImpl().updateSite(sitelist.get(i), con);
		}
	}

	/**
	 * 删除一条线路时，相关站点的LineId、Order、LineName 2016年7月27日下午2:29:53
	 * 
	 * @author mischief7
	 * @param lineId
	 * @param com
	 * @throws SQLException
	 */
	public void deleteOneLine(int lineid, Connection con) throws SQLException {
		Line line = new Line();
		line = new LineDaoImpl().getLineById(con, lineid);
		String[] site_ids = line.getSiteId().split(",");
		for (int i = 0; i < site_ids.length; i++) {
			Site site = new Site();
			site = new SiteDaoImpl().getSiteById(Integer.valueOf(site_ids[i])
					.intValue(), con);
			String[] site_lines = site.getLineId().split(",");
			String[] site_orders = site.getOrder().split(",");
			String[] site_names = site.getLineName().split(",");
			int flag_pos = -1;
			for (int j = 0; j < site_lines.length; j++) {
				if (site_lines[j].equals(line.getLineId() + "")) {
					flag_pos = j;
					break;
				}
			}
			if (flag_pos != -1) {
				site.setLineId("");
				site.setOrder("");
				site.setLineName("");
				for (int j = 0; j < site_lines.length; j++) {
					if (j != flag_pos) {
						site.setLineId(site.getLineId() + site_lines[j] + ",");
						site.setOrder(site.getOrder() + site_orders[j] + ",");
						site.setLineName(site.getLineName() + site_names[j]
								+ ",");
					}
				}// for j

				String temp1 = site.getLineId();
				if (temp1 != null && !temp1.isEmpty()) {
					if (temp1.charAt(temp1.length() - 1) == ',') {
						temp1 = temp1.substring(0, temp1.length() - 1);
						site.setLineId(temp1);
					}
				}
				String temp2 = site.getOrder();
				if (temp2 != null && !temp2.isEmpty()) {
					if (temp2.charAt(temp2.length() - 1) == ',') {
						temp2 = temp2.substring(0, temp2.length() - 1);
						site.setOrder(temp2);
					}
				}
				String temp3 = site.getLineName();
				if (temp3 != null && !temp3.isEmpty()) {
					if (temp3.charAt(temp3.length() - 1) == ',') {
						temp3 = temp3.substring(0, temp3.length() - 1);
						site.setLineName(temp3);
					}
				}
				new SiteDaoImpl().updateSite(site, con);
			}// if flag_pos != -1

		}// for i
	}

	/**
	 * 返回未规划线路的站点 2016年7月27日下午3:14:51
	 * 
	 * @author mischief7
	 * @param ids
	 * @param con
	 * @return
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public ArrayList<Site> getNotSiteList(Connection con) {
		ArrayList<Site> temp = new ArrayList<Site>();
		temp = new SiteDaoImpl().getAllSite(con);
		ArrayList<Site> sitelist = new ArrayList<Site>();
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getLineId() == null
					|| temp.get(i).getLineId().equals("")
					|| temp.get(i).getLineId() == "") {
				sitelist.add(temp.get(i));
			}
		}
		return sitelist;
	}

	public String reNewName(Line line, Connection con) throws SQLException {
		String name = "";
		name = line.getName();
		ArrayList<Line> list = new ArrayList<Line>();
		list = new LineDaoImpl().getAllLine(con);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(name)) {
				name = name + "_";
				i = 0;
				continue;
			}
		}
		return name;
	}

	/**
	 * 部分生成线路——增加站点的路线信息 2016年7月27日下午3:37:07
	 * 
	 * @author mischief7
	 * @param con
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public void addMoreLineOfSite(ArrayList<Line> list, Connection con)
			throws NumberFormatException, SQLException {
		for (int i = 0; i < list.size(); i++) {
			int lid = new LineDaoImpl().getLineIdByName(con, list.get(i)
					.getName());
			if (lid != -1) {
				list.get(i).setLineId(lid);
				list.get(i).setName("智能路线" + list.get(i).getLineId() + "_Z");
				list.get(i).setName(reNewName(list.get(i), con));
			}
			new LineDaoImpl().updateLine(con, list.get(i));
		}
		Site site = new Site();
		for (int i = 0; i < list.size(); i++) {
			String[] site_ids = list.get(i).getSiteId().split(",");
			for (int j = 0; j < site_ids.length; j++) {
				site = new SiteDaoImpl().getSiteById(
						Integer.valueOf(site_ids[j]).intValue(), con);
				if(site != null){
					if (site.getLineId() == null || site.getLineId().equals("")) {
						site.setLineId(list.get(i).getLineId() + ",");
					} else if (site.getLineId().charAt(
							site.getLineId().length() - 1) == ',') {
						site.setLineId(site.getLineId() + list.get(i).getLineId()
								+ ",");
					} else {
						site.setLineId(site.getLineId() + ","
								+ list.get(i).getLineId());
					}
	
					int order = j + 1;
	
					if (site.getOrder() == null || site.getOrder().equals("")) {
						site.setOrder(order + ",");
					} else if (site.getOrder().charAt(site.getOrder().length() - 1) == ',') {
						site.setOrder(site.getOrder() + order + ",");
					} else {
						site.setOrder(site.getOrder() + "," + order);
					}
	
					if (site.getLineName() == null || site.getLineName().equals("")) {
						site.setLineName(list.get(i).getName() + ",");
					} else if (site.getLineName().charAt(
							site.getLineName().length() - 1) == ',') {
						site.setLineName(site.getLineName() + list.get(i).getName()
								+ ",");
					} else {
						site.setLineName(site.getLineName() + ","
								+ list.get(i).getName());
					}
					new SiteDaoImpl().updateSite(site, con);
				}
			}
		}
		ArrayList<Site> sitelist = new SiteDaoImpl().getAllSite(con);
		for (int i = 0; i < sitelist.size(); i++) {
			String temp1 = sitelist.get(i).getLineId();
			if (temp1 != null && !(temp1.isEmpty() || temp1.equals(""))) {
				if (temp1.charAt(temp1.length() - 1) == ',') {
					temp1 = temp1.substring(0, temp1.length() - 1);
					sitelist.get(i).setLineId(temp1);
				}
			}
			String temp2 = sitelist.get(i).getOrder();
			if (temp2 != null && !(temp2.isEmpty() || temp1.equals(""))) {
				if (temp2.charAt(temp2.length() - 1) == ',') {
					temp2 = temp2.substring(0, temp2.length() - 1);
					sitelist.get(i).setOrder(temp2);
				}
			}
			String temp3 = sitelist.get(i).getLineName();
			if (temp3 != null && !(temp3.isEmpty() || temp1.equals(""))) {
				if (temp3.charAt(temp3.length() - 1) == ',') {
					temp3 = temp3.substring(0, temp3.length() - 1);
					sitelist.get(i).setLineName(temp3);
				}
			}

		}
		new SiteDaoImpl().updateListSite(sitelist, con);
	}

	/**
	 * 手动增加线路时更新站点信息 2016年7月28日下午10:19:33
	 * 
	 * @author mischief7
	 * @param line
	 *            , con
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void haddLineOfSite(Line line, Connection con)
			throws NumberFormatException, SQLException {
		String[] site_ids = line.getSiteId().split(",");
		Site site = new Site();

		for (int j = 0; j < site_ids.length; j++) {
			site = new SiteDaoImpl().getSiteById(Integer.valueOf(site_ids[j])
					.intValue(), con);
			if (site.getLineId() == null || site.getLineId().equals("")) {
				site.setLineId(line.getLineId() + "");
			} else {
				site.setLineId(site.getLineId() + "," + line.getLineId());
			}
			if (site.getOrder() == null || site.getOrder().equals("")) {
				int order = j + 1;
				site.setOrder(order + "");
			} else {
				int order = j + 1;
				site.setOrder(site.getOrder() + "," + order);
			}
			if (site.getLineName() == null || site.getLineName().equals("")) {
				site.setLineName(line.getName() + "");
			} else {
				site.setLineName(site.getLineName() + "," + line.getName());
			}

			new SiteDaoImpl().updateSite(site, con);
		}
	}

	/**
	 * 修改一条线路的人数 2016年7月28日下午12:42:17
	 * 
	 * @author jimolonely
	 * @param line
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public void modifyLineNum(Line line, Connection con)
			throws NumberFormatException, SQLException {
		String[] siteIds = line.getSiteId().split(",");
		SiteDaoImpl sdi = new SiteDaoImpl();
		LineDaoImpl ldi = new LineDaoImpl();
		int sum = 0;// line最后累加每个站点的人数结果
		for (int i = 0; i < siteIds.length; i++) {
			Site s = sdi.getSiteById(Integer.parseInt(siteIds[i]), con);
			String sLineIds = s.getLineId();
			int siteNum = s.getPeoNum();
			/* 考虑站点线路为空的情况 */
			if (sLineIds==null) {
				s.setLineId(line.getLineId() + "");
				s.setOrder((i + 1) + "");
				s.setLineName(line.getName());
				sum += siteNum;
				/* 更新此站点存入数据库 */
				sdi.updateSite(s, con);
				continue;
			}else if(sLineIds.equals("")){
				s.setLineId(line.getLineId() + "");
				s.setOrder((i + 1) + "");
				s.setLineName(line.getName());
				sum += siteNum;
				/* 更新此站点存入数据库 */
				sdi.updateSite(s, con);
				continue;
			}
			String[] lines = sLineIds.split(",");
			boolean isEven = false;
			int len = lines.length;
			if (0!=len&&siteNum % len == 1) {
				isEven = true;
			}
			for (int j = 0; j < len; j++) {
				if(!lines[j].equals("")){
					Line l = ldi.getLineById(con, Integer.parseInt(lines[j]));
					if (null != l) {
						l.setNum(l.getNum() - (siteNum / len - siteNum / (len + 1)));
						ldi.updateLine(con, l);
					}
				}
			}
			if (isEven) {// 最后一个且站点人数是奇数的话要多减一
				Line ll = ldi
						.getLineById(con, Integer.parseInt(lines[len - 1]));
				if(ll!=null){
					ll.setNum(ll.getNum() - 1);
					ldi.updateLine(con, ll);
				}
			}
			/* 最后一个是新加进来的line分配到的人数 */
			sum += siteNum - siteNum * len / (len + 1);
			/* 加入line到此site后面 */
			s.setLineId(sLineIds + "," + line.getLineId());
			/* 改变此站点order */
			s.setOrder(s.getOrder() + "," + (i + 1));
			/* 更新站点线路名称 */
			s.setLineName(s.getLineName() + "," + line.getName());
			/* 更新此站点存入数据库 */
			sdi.updateSite(s, con);
		}
		/* 更新line人数 */
		line.setNum(sum);
		/* 存入数据库 */
		ldi.updateLine(con, line);
	}

	/**
	 * 删除一条路线 2016年7月29日上午11:00:17
	 * 
	 * @author jimolonely
	 * @param line
	 * @param con
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void deleteOneLine(Line line, Connection con)
			throws NumberFormatException, SQLException {
		String[] siteIds = line.getSiteId().split(",");
		SiteDaoImpl sdi = new SiteDaoImpl();
		LineDaoImpl ldi = new LineDaoImpl();
		int sum = 0;// line最后累加每个站点的人数结果
		for (int i = 0; i < siteIds.length; i++) {
			Site s = sdi.getSiteById(Integer.parseInt(siteIds[i]), con);
			String sLineIds = s.getLineId();
			String[] lines = sLineIds.split(",");
			String[] sOrders = s.getOrder().split(",");
			String[] sLineName = s.getLineName().split(",");

			int siteNum = s.getPeoNum();
			int len = lines.length;
			int key = 0;
			String newLineIds = "";
			String newOrder = "";
			String newLineName = "";
			for (int k = 0; k < len; k++) {
				if (lines[k].equals(line.getLineId() + "")) {
					key = k;
					continue;
				}
				newLineIds += lines[k] + ",";
				newOrder += sOrders[k] + ",";
				newLineName += sLineName[k] + ",";
			}
			// 去掉最后的分号
			if (key != 0 && newLineIds.length() > 0) {
				System.out.println(newLineIds.length());
				newLineIds = newLineIds.substring(0, newLineIds.length() - 1);
				newOrder = newOrder.substring(0, newOrder.length() - 1);
				newLineName = newLineName
						.substring(0, newLineName.length() - 1);
			}
			// 更新site线路
			s.setLineId(newLineIds);
			s.setLineName(newLineName);
			s.setOrder(newOrder);
			/* 下面更新其他线路的人数 */
			int[] b = new int[len];
			int bb = siteNum;
			int[] a = new int[len];
			int aa = siteNum;
			for (int k = 0; k < len; k++) {
				if (k == len - 1) {
					b[k] = bb;
					break;
				}
				b[k] = siteNum / len;
				bb -= b[k];
			}
			for (int k = 0; k < len; k++) {
				if (key == k) {
					continue;
				}
				if (k == len - 2) {
					a[k] = aa;
					break;
				}
				a[k] = siteNum / (len - 1);
				aa -= a[k];
			}
			for (int k = 0; k < len; k++) {
				if (k != key&&!lines[k].equals("")) {
					Line ll = ldi.getLineById(con, Integer.parseInt(lines[k]));
					if(null!=ll){
						ll.setNum(ll.getNum() + a[k] - b[k]);
						ldi.updateLine(con, ll);
					}
				}
			}

			/* 更新此站点存入数据库 */
			sdi.updateSite(s, con);
		}
		/* 删除线路 */
		ldi.deleteLine(line.getLineId(), con);
	}
	
	/**
	 * 智能路线的一连串删除
	 * 2016年7月30日下午2:45:11
	 * @author mischief7
	 * @param line
	 * @param con
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void deleteIntelLine(Line line, Connection con) throws NumberFormatException, SQLException{
		ArrayList<Line> linelist = new ArrayList<Line>();
		linelist.add(line);
		Line temp = null;
		for(int k=0;k<linelist.size();k++){
			if(linelist.get(k).getRate() < 0.0 && linelist.get(k).getRate() != 0.0){	//智能路线的删除
				String[] sites = linelist.get(k).getSiteId().split(",");
				for(int i=0;i<sites.length;i++){
					if(!sites[i].equals("0")){
						Site site = new Site();
						site = new SiteDaoImpl().getSiteById(Integer.valueOf(sites[i]).intValue(), con);
						String[] lines = site.getLineId().split(",");
						for(int j=0;j<lines.length;j++){
							if(lines[j] != null && !lines[j].equals("")){
								temp = new LineDaoImpl().getLineById(con, Integer.valueOf(lines[j]).intValue());
								int flag = 1;
								for(int ii=0;ii<linelist.size();ii++){
									if(linelist.get(ii).getLineId() == temp.getLineId()){
										flag = 0;
										break;
									}//if
								}
								if(flag == 1){
									linelist.add(temp);
								}
								
							}
						}//for j
						site.setLineId("");
						site.setOrder("");
						site.setLineName("");
						new SiteDaoImpl().updateSite(site, con);
					}//if !0
				}//sites
				new LineDaoImpl().deleteLine(linelist.get(k).getLineId(), con);
			}//判断智能
		}
		
	}
	
	public void modifyLineOfStaff(Connection con){
		ArrayList<Site> allsite = new ArrayList<Site>();
		allsite = new SiteDaoImpl().getAllSite(con);
		for(int i=0;i<allsite.size();i++){
			allsite.get(i).setBufftag(0);
		}
		ArrayList<Staff> allstaff = new ArrayList<Staff>();
		allstaff = new StaffDaoImpl().getAllStaff(con);
		int temp_site;
		int j, order;
		for(int i=0;i<allstaff.size();i++){
			temp_site = allstaff.get(i).getSiteId();
			for(j=0;j<allsite.size();j++){
				if(allsite.get(j).getSiteId() == temp_site){
					break;
				}
			}
			String[] lines = allsite.get(j).getLineId().split(",");
			if(lines.length>0&&lines[0]!=null&&!lines[0].equals("")){
				order =  allsite.get(j).getPeoNum() / lines.length;
				if(allsite.get(j).getBufftag() / order < lines.length){
					allstaff.get(i).setLineId(Integer.valueOf(lines[allsite.get(j).getBufftag() / order]).intValue());
				}else{
					allstaff.get(i).setLineId(Integer.valueOf(lines[lines.length-1]).intValue());
				}
			}
			else{
				allstaff.get(i).setLineId(-1);
			}
			allsite.get(j).setBufftag(allsite.get(j).getBufftag() + 1);
			
		}
		
		new StaffDaoImpl().updateListStaff(allstaff, con);
		
	}
	
	public void modifyLineName(String old_name, int lineId, Connection con) throws SQLException{
		Line line = null;
		line = new LineDaoImpl().getLineById(con, lineId);
		ArrayList<Site> allsite = new ArrayList<Site>();
		allsite = new SiteDaoImpl().getAllSite(con);
		int flag = 0;
		for(int i=0;i<allsite.size();i++){
			String[] linenames = allsite.get(i).getLineName().split(",");
			if(linenames.length > 0){
				flag = 0;
				for(int j=0;j<linenames.length;j++){
					if(linenames[j].equals(old_name)){
						linenames[j] = line.getName();
						flag = 1;
					}
				}
				if(flag == 1){
					String newnames = linenames[0];
					for(int j=1;j<linenames.length;j++){
						newnames += "," + linenames[j];
					}
					allsite.get(i).setLineName(newnames);
					new SiteDaoImpl().updateSite(allsite.get(i), con);
				}
			}
		}
		
	}
	
}
