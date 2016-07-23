package edu.swjtu.intelligent;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import edu.swjtu.model.Car;
import edu.swjtu.model.Line;
import edu.swjtu.model.Site;

public class PlanRoute {
	private double all_distance;
	private double ave_seatrate;
	private double all_distance_w;
	private double ave_seatrate_w;
	private double weight;
	private int fin_num;

	public ArrayList<Site> copySiteList(ArrayList<Site> sitelist) {

		ArrayList<Site> l2 = new ArrayList<Site>();
		int len = sitelist.size();
		for (int i = 0; i < len; i++) {
			l2.add(new Site());
			l2.get(i).setLatitude(sitelist.get(i).getLatitude());
			l2.get(i).setLongitude(sitelist.get(i).getLongitude());
			l2.get(i).setPeoNum(sitelist.get(i).getPeoNum());
		}
		return l2;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Line> intelligentLine(double min_rate, double max_distance,
			ArrayList<Site> t_sitelist, ArrayList<Car> carlist, Site fac_site,
			double dis_w, double ave_w) {
		ArrayList<Line> linelist = new ArrayList<Line>();
		ArrayList<Site> sitelist = copySiteList(t_sitelist);
		int num_site = 0, num_seat = 0, num_free = 0;
		int i;
		double temp_weight;
		int carListLen = carlist.size();
		for (i = 0; i < carListLen; i++)
			num_seat += carlist.get(i).getNumber();
		int siteListLen = sitelist.size();
		for (i = 0; i < siteListLen; i++)
			num_site += sitelist.get(i).getPeoNum();
		num_free = num_seat - num_site;
		if (num_free < 0) // 厂车座位总数小于人数
			return linelist;

		if (min_rate == 0)
			min_rate = 1.0;
		
		if(max_distance == 0.0){	//设置最大距离为最远点距工厂路程的3/2
			double temp_dis = 0.0;
			for(int j=0;j<siteListLen;j++){
				double t_dis = cal_disByxy(sitelist.get(j), fac_site);
				if(t_dis > temp_dis){
					temp_dis = t_dis;
				} //if
			} //for:j
			max_distance = temp_dis * 1.5;
		} //if
		else if(max_distance == -1.0){		//不考虑路程，每一条路线都满足最低乘坐率
			max_distance = Double.MAX_VALUE;
		}
		
		if (dis_w == 0.0 && ave_w == 0.0) {
			all_distance_w = 1;
			ave_seatrate_w = 0;
		} else {
			all_distance_w = dis_w;
			ave_seatrate_w = ave_w;
		}
		long sortTime = 0;
		long calcuTime = 0;
		for (i = 0; i < siteListLen; i++) {

			long start = System.currentTimeMillis();
			sitelist = getSortedSites(i, t_sitelist, fac_site);
			long end = System.currentTimeMillis(); // 记录结束时间
			sortTime += (end - start);
			// System.out.println("排序第"+i+"次运行时间："+(end-start)+"ms");

			// for (int j = 0; j < sitelist.size(); j++)
			// System.out.println("站点人数：" + t_sitelist.get(j).getPeoNum());

			all_distance = 0.0;
			ave_seatrate = 0.0;
			long start1 = System.currentTimeMillis();
			cal_DisAndRate(i, min_rate, max_distance, num_free, sitelist, carlist, fac_site);
			long end1 = System.currentTimeMillis(); // 记录结束时间
			calcuTime += (end1 - start1);
			System.out.println("计算第" + i + "次运行时间：" + (end1 - start1) + "ms");

			if (i == 0) {
				weight = all_distance * all_distance_w * 1.0 + ave_seatrate
						* ave_seatrate_w * 1.0;
				fin_num = i;
			} else {
				temp_weight = all_distance * all_distance_w * 1.0
						+ ave_seatrate * ave_seatrate_w * 1.0;
				if (temp_weight < weight) {
					fin_num = i;
					weight = temp_weight;
				}
			}// else

			// System.out.println("all_distance=" + all_distance);

		}// for

		System.out.println("排序总时间：" + sortTime + "ms  计算总时间：" + calcuTime
				+ "ms");

		long start1 = System.currentTimeMillis();
		linelist = getLineSite(min_rate, max_distance, carlist, t_sitelist, fac_site, fin_num);
		long end1 = System.currentTimeMillis(); // 记录结束时间
		System.out.println("取得线路时间：" + (end1 - start1) + "ms");
		// System.out.println(fin_num);
		return linelist;
	}

	@SuppressWarnings("unchecked")
	private void cal_DisAndRate(int number, double min_rate, double max_distance, int num_free,
			ArrayList<Site> t_sitelist, ArrayList<Car> carlist, Site fac_site) {
		// double[] pos = new double[sitelist.size()];
		// ArrayList<Site> temp_sitelist = new ArrayList<Site>();
		// //按极坐标方式排好序的站点序列
		// temp_sitelist = 0;

		int i, record_s = 0, temp_seat; // record是第几个车/站点
		int beg_s, end_s;

		ArrayList<Site> sitelist = copySiteList(t_sitelist); // 一条路线的站点序列
		int siteListLen = sitelist.size();
		for (i = 0, beg_s = record_s; record_s < siteListLen; i++) { // i表示车和路线的序号
			temp_seat = carlist.get(i).getNumber();

			// t_sitelist.clear();
			// t_sitelist = null;

			while (record_s < siteListLen) {
				// System.out.println("车上空闲座位数量：" + temp_seat);
				if (temp_seat >= sitelist.get(record_s).getPeoNum()) {
					temp_seat -= sitelist.get(record_s).getPeoNum();
					sitelist.get(record_s).setPeoNum(0);
					if (temp_seat == 0 || record_s == siteListLen - 1)
						break;
					record_s++;
				} else if ((carlist.get(i).getNumber() - temp_seat) * 1.0
						/ (carlist.get(i).getNumber() * 1.0) < min_rate) {
					sitelist.get(record_s).setPeoNum(
							sitelist.get(record_s).getPeoNum() - temp_seat);
					break;
				} else {
					record_s--;
					break;
				}
			} // while:true

			end_s = record_s;
			if(getArc(sitelist.get(beg_s), sitelist.get(end_s), fac_site) >0.00001){//此时区域张角大于10度
				long start = System.currentTimeMillis();
				
				double temp_dis = cal_distance(beg_s, end_s, sitelist, fac_site);
				while(temp_dis > max_distance){
					temp_seat += t_sitelist.get(record_s).getPeoNum() - sitelist.get(record_s).getPeoNum();
					sitelist.get(record_s).setPeoNum(t_sitelist.get(record_s).getPeoNum());
					end_s--;
					record_s--;
					temp_dis = cal_distance(beg_s, end_s, sitelist, fac_site);
				}
				
				
				all_distance += cal_distance(beg_s, end_s, sitelist, fac_site);
				
				
				
				long end = System.currentTimeMillis(); // 记录结束时间
				System.out.println("每次分支限界法时间："+(end-start)+"ms");
			}
			else{
				long start = System.currentTimeMillis();
				all_distance += greAlgorithm(beg_s, end_s, sitelist, fac_site);
				long end = System.currentTimeMillis(); // 记录结束时间
				//sortTime += (end - start);
				System.out.println("每次贪心时间："+(end-start)+"ms");
			}
			
			ave_seatrate += (carlist.get(i).getNumber() - temp_seat) * 1.0
					/ (carlist.get(i).getNumber() * 1.0);

			if (sitelist.get(record_s).getPeoNum() == 0)
				record_s++;

			beg_s = record_s;
		} // for:i

	}

	private double cal_distance_t(int beg_s, int end_s,
			ArrayList<Site> sitelist, Site fac_site) {
		int i;
		double dis = 0.0, x, y;
		// System.out.println("beg_s=" + beg_s + "   end_s=" + end_s);
		for (i = beg_s; i < end_s; i++) {
			x = sitelist.get(i).getLatitude()
					- sitelist.get(i + 1).getLatitude();
			y = sitelist.get(i).getLongitude()
					- sitelist.get(i + 1).getLongitude();
			// System.out.println("x1 x2:" +
			// Double.parseDouble(sitelist.get(i).getLatitude()) +"===" +
			// Double.parseDouble(sitelist.get(i+1).getLatitude()) );
			// System.out.println("y1 y2:" +
			// Double.parseDouble(sitelist.get(i).getLongitude()) +"===" +
			// Double.parseDouble(sitelist.get(i+1).getLongitude()) );
			// System.out.println("xxx:" + Math.sqrt(x*x + y*y));
			dis += Math.sqrt(x * x + y * y);
		}
		x = sitelist.get(beg_s).getLatitude()
				- fac_site.getLatitude();
		y = sitelist.get(beg_s).getLongitude()
				- fac_site.getLongitude();
		dis += Math.sqrt(x * x + y * y);
		// System.out.println("xxx:" + Math.sqrt(x*x + y*y));
		x = sitelist.get(end_s).getLatitude()
				- fac_site.getLatitude();
		y = sitelist.get(end_s).getLongitude()
				- fac_site.getLongitude();
		dis += Math.sqrt(x * x + y * y);
		// System.out.println("xxx:" + Math.sqrt(x*x + y*y));
		// System.out.println("dis=" + dis);
		return dis;
	}

	Comparator<SearchRoute> OrderIsdn = new Comparator<SearchRoute>() {

		@Override
		public int compare(SearchRoute o1, SearchRoute o2) {
			if (o1.weight < o2.weight) {
				return -1;
			} else if (o1.weight > o2.weight) {
				return 1;
			} else {
				return 0;
			}
		}
	};

	public double cal_distance(int beg_s, int end_s, ArrayList<Site> sitelist,
			Site fac_site) {
		double dis = 0.0;
		double max_dis = greAlgorithm(beg_s, end_s, sitelist, fac_site);
		Queue<SearchRoute> prioQue = new PriorityQueue<SearchRoute>(
				sitelist.size(), OrderIsdn);
		int i;
		SearchRoute sr = new SearchRoute();
		sr.weight = 0.0;
		sr.pass_path.add(-1);
		for (i = beg_s; i <= end_s; i++) {
			sr.notpass_path.add(i);
		}
		prioQue.add(sr);
		while (!prioQue.isEmpty()) {
			sr = prioQue.poll();
			// System.out.println(sr.pass_path);
			if (sr.notpass_path.size() == 0) {
				return sr.weight;
			} else {
				for (i = 0; i < sr.notpass_path.size(); i++) {
					int temp_s1 = sr.pass_path.get(sr.pass_path.size() - 1)
							.intValue();
					int temp_s2 = sr.notpass_path.get(i).intValue();
					if (temp_s1 == -1) {
						dis = cal_disByxy(fac_site, sitelist.get(temp_s2));
					} else {
						dis = cal_disByxy(sitelist.get(temp_s1),
								sitelist.get(temp_s2));
					}
					if (sr.weight + dis <= max_dis) {
						SearchRoute temp_sr = new SearchRoute();
						temp_sr.weight = sr.weight + dis;
						for (int j = 0; j < sr.pass_path.size(); j++) {
							temp_sr.pass_path.add(sr.pass_path.get(j)
									.intValue());
						}
						for (int j = 0; j < sr.notpass_path.size(); j++) {
							temp_sr.notpass_path.add(sr.notpass_path.get(j)
									.intValue());
							// System.out.println(sr.notpass_path.get(j).intValue());
						}
						// System.out.println(temp_sr.notpass_path.size());
						temp_sr.pass_path.add(temp_s2);
						temp_sr.notpass_path.remove(i);
						prioQue.add(temp_sr);

					}// if
				}
			}// else
		}// while
		return max_dis;
	}

	public ArrayList<Integer> get_oneline(int beg_s, int end_s,
			ArrayList<Site> sitelist, Site fac_site) {
		double dis = 0.0;
		double max_dis = greAlgorithm(beg_s, end_s, sitelist, fac_site);
		Queue<SearchRoute> prioQue = new PriorityQueue<SearchRoute>(
				sitelist.size(), OrderIsdn);
		int i;
		SearchRoute sr = new SearchRoute();
		sr.weight = 0.0;
		sr.pass_path.add(-1);
		for (i = beg_s; i <= end_s; i++) {
			sr.notpass_path.add(i);
		}
		prioQue.add(sr);
		while (!prioQue.isEmpty()) {
			sr = prioQue.poll();
			// System.out.println(sr.pass_path);
			if (sr.notpass_path.size() == 0) {
				return sr.pass_path;
			} else {
				for (i = 0; i < sr.notpass_path.size(); i++) {
					int temp_s1 = sr.pass_path.get(sr.pass_path.size() - 1)
							.intValue();
					int temp_s2 = sr.notpass_path.get(i).intValue();
					if (temp_s1 == -1) {
						dis = cal_disByxy(fac_site, sitelist.get(temp_s2));
					} else {
						dis = cal_disByxy(sitelist.get(temp_s1),
								sitelist.get(temp_s2));
					}
					if (sr.weight + dis <= max_dis) {
						SearchRoute temp_sr = new SearchRoute();
						temp_sr.weight = sr.weight + dis;
						for (int j = 0; j < sr.pass_path.size(); j++) {
							temp_sr.pass_path.add(sr.pass_path.get(j)
									.intValue());
						}
						for (int j = 0; j < sr.notpass_path.size(); j++) {
							temp_sr.notpass_path.add(sr.notpass_path.get(j)
									.intValue());
							// System.out.println(sr.notpass_path.get(j).intValue());
						}
						// System.out.println(temp_sr.notpass_path.size());
						temp_sr.pass_path.add(temp_s2);
						temp_sr.notpass_path.remove(i);
						prioQue.add(temp_sr);

					}// if
				}
			}// else
		}// while
		return null;
	}

	public double greAlgorithm(int beg_s, int end_s, ArrayList<Site> sitelist,
			Site fac_site) {
		double dis = 0.0, temp_dis = Double.MAX_VALUE;
		int temp_num = 0;
		ArrayList<Site> temp_sitelist = new ArrayList<Site>();
		Site acc_pos = new Site();
		acc_pos.setLatitude(fac_site.getLatitude());
		acc_pos.setLongitude(fac_site.getLongitude());
		int i;
		for (i = beg_s; i <= end_s; i++) {
			temp_sitelist.add(new Site());
			temp_sitelist.get(i - beg_s).setLatitude(
					sitelist.get(i).getLatitude());
			temp_sitelist.get(i - beg_s).setLongitude(
					sitelist.get(i).getLongitude());
		}
		while (temp_sitelist.size() > 0) {
			temp_dis = Double.MAX_VALUE;
			for (i = 0; i < temp_sitelist.size(); i++) {
				double temp = cal_disByxy(acc_pos, temp_sitelist.get(i));
				if (temp < temp_dis) {
					temp_num = i;
					temp_dis = temp;
				}
			}
			// System.out.println("dis=" + temp_dis);
			dis += temp_dis;
			acc_pos.setLongitude(temp_sitelist.get(temp_num).getLongitude());
			acc_pos.setLatitude(temp_sitelist.get(temp_num).getLatitude());
			temp_sitelist.remove(temp_num);

		}
		return dis;
	}
	
	public ArrayList<Integer> get_greline(int beg_s, int end_s, ArrayList<Site> sitelist, Site fac_site) {
		double temp_dis = Double.MAX_VALUE;
		SearchRoute sr = new SearchRoute();
		sr.weight = 0.0;
		sr.pass_path.add(-1);
		int i, temp_num = 0;
		for (i = beg_s; i <= end_s; i++) {
			sr.notpass_path.add(i);
		}
		while (sr.notpass_path.size() > 0) {
			temp_dis = Double.MAX_VALUE;
			for (i = 0; i < sr.notpass_path.size(); i++) {
				double temp = 0;
				if(sr.pass_path.get(sr.pass_path.size()-1) == -1){
					temp=cal_disByxy(fac_site, sitelist.get(sr.notpass_path.get(i)));
				}else{
					temp=cal_disByxy(sitelist.get(sr.pass_path.get(sr.pass_path.size()-1)), sitelist.get(sr.notpass_path.get(i)));
				}
				if (temp < temp_dis) {
					temp_num = i;
					temp_dis = temp;
				}
			}
//			System.out.println("temp_dis=" + temp_dis);
			sr.weight += temp_dis;
			sr.pass_path.add(sr.notpass_path.get(temp_num));
			sr.notpass_path.remove(temp_num);
		}
//		System.out.println(sr.weight);
		return sr.pass_path;
	}

	private double cal_disByxy(Site s1, Site s2) {
		double x = s1.getLatitude()
				- s2.getLatitude();
		double y = s1.getLongitude()
				- s2.getLongitude();
		return Math.sqrt(x * x + y * y);
	}

	public ArrayList<Line> getLineSite(double min_rate, double max_distance, ArrayList<Car> carlist,
			ArrayList<Site> sites, Site fac_site, int start) {
		ArrayList<Site> sitelist = copySiteList(sites);
		ArrayList<Line> linelist = new ArrayList<Line>();;
		sitelist = getSortedSites(start, sites, fac_site);

		double long_dis = 0.0;
		int sym = 0;
		int i, record_s = 0, temp_seat; // record是第几个车/站点
		int beg_s, end_s;

		DecimalFormat df = new DecimalFormat("0.000000 ");
		for (i = 0, beg_s = record_s; record_s < sitelist.size(); i++) { // i表示车和路线的序号
			temp_seat = carlist.get(i).getNumber();
			int siteListLen = sitelist.size();
			while (record_s < siteListLen) {
				if (temp_seat >= sitelist.get(record_s).getPeoNum()) {
					temp_seat -= sitelist.get(record_s).getPeoNum();
					sitelist.get(record_s).setPeoNum(0);
					if (temp_seat == 0 || record_s == siteListLen - 1)
						break;
					record_s++;
				} else if ((carlist.get(i).getNumber() - temp_seat) * 1.0
						/ (carlist.get(i).getNumber() * 1.0) < min_rate) {
					sitelist.get(record_s).setPeoNum(
							sitelist.get(record_s).getPeoNum() - temp_seat);
					break;
				} else {
					record_s--;
					break;
				}
			} // while:true

			end_s = record_s;
			// sym = beg_s;
			long_dis = 0.0;

			if(getArc(sitelist.get(beg_s), sitelist.get(end_s), fac_site) >0.00001){//此时区域张角大于20度
				
				double temp_dis = cal_distance(beg_s, end_s, sitelist, fac_site);
				while(temp_dis > max_distance){
					temp_seat += sites.get(record_s).getPeoNum() - sitelist.get(record_s).getPeoNum();
					sitelist.get(record_s).setPeoNum(sites.get(record_s).getPeoNum());
					end_s--;
					record_s--;
					temp_dis = cal_distance(beg_s, end_s, sitelist, fac_site);
				}
			}

			
			ArrayList<Integer> onepath = get_oneline(beg_s, end_s, sitelist,
					fac_site);
			
			linelist.add(new Line());
			linelist.get(i).setCarId(carlist.get(i).getCarId());
			linelist.get(i).setNum(carlist.get(i).getNumber()-temp_seat);
			String temp_line = "";
			for (int j = onepath.size() - 1; j > 0; j--) {
				temp_line += sitelist.get(onepath.get(onepath.size() - 1)).getSiteId() + ",";
			}
			temp_line += fac_site.getSiteId();
			linelist.get(i).setSiteId(temp_line);
			
			System.out.println("{lng:[["
					+ df.format(sitelist.get(
							onepath.get(onepath.size() - 1)).getLongitude())
					+ ","
					+ df.format(sitelist.get(
							onepath.get(onepath.size() - 1)).getLatitude())
					+ "],");
			for (int j = onepath.size() - 2; j > 0; j--) {
				if (j != sym)
					System.out
							.println("["
									+ df.format(sitelist
											.get(onepath.get(j)).getLongitude())
									+ ","
									+ df.format(sitelist
											.get(onepath.get(j)).getLatitude())
									+ "]" + ",");
			}
			System.out.println("["
					+ df.format(fac_site.getLongitude())
					+ ","
					+ df.format(fac_site.getLatitude())
					+ "]]" + "},");
			// System.out.println("");
			System.out.println("");

			if (sitelist.get(record_s).getPeoNum() == 0)
				record_s++;

			beg_s = record_s;
		} // for:i

		return linelist;
	}

	public void quicksort(ArrayList<Site> sites, Site s, int left, int right) {
		int dp;
		if (left < right) {
			dp = partition(sites, s, left, right);
			quicksort(sites, s, left, dp - 1);
			quicksort(sites, s, dp + 1, right);
		}
	}

	private boolean judgeSite(Site s1, Site s2, Site s) {
		double l1 = s1.getLatitude();
		double l2 = s2.getLatitude();
		if (s.getLatitude() == s1.getLatitude()) {
			l1 = 0.000001;
		} else if (s.getLatitude() == s2.getLatitude()) {
			l2 = 0.000001;
		}
		double tan1 = (s1.getLongitude() - s.getLongitude())
				/ (s.getLatitude() - l1);
		double tan2 = (s2.getLongitude() - s.getLongitude())
				/ (s.getLatitude() - l2);
		return (tan1 - tan2) < 0;
	}

	int partition(ArrayList<Site> sites, Site s, int left, int right) {
		Site pivot = sites.get(left);
		while (left < right) {
			while (left < right && judgeSite(sites.get(right), pivot, s))
				right--;
			if (left < right) {
				sites.set(left, sites.get(right));
				left++;
			}
			while (left < right && judgeSite(pivot, sites.get(left), s))
				left++;
			if (left < right) {
				sites.set(right, sites.get(left));
				right--;
			}
		}
		sites.set(left, pivot);
		return left;
	}

	public ArrayList<Site> getSortedSites(int number, ArrayList<Site> sites,
			Site s) {

		Site beginSite = sites.get(number);// 保留开始的

		ArrayList<Site> up = new ArrayList<Site>();
		ArrayList<Site> down = new ArrayList<Site>();

		for (Site st : sites) {
			if (st.getLatitude() > s.getLatitude()) {
				up.add(st);
			} else {
				down.add(st);
			}
		}

		// System.out.println("down:"+down.size());
		quicksort(up, s, 0, up.size() - 1);
		quicksort(down, s, 0, down.size() - 1);

		int t = 0;
		ArrayList<Site> re = new ArrayList<Site>();
		if ((t = up.indexOf(beginSite)) != -1) {
			up.addAll(down);// 链接
			re.addAll(up.subList(t, up.size()));
			re.addAll(up.subList(0, t));
		} else if ((t = down.indexOf(beginSite)) != -1) {
			down.addAll(up);
			re.addAll(down.subList(t, down.size()));
			re.addAll(down.subList(0, t));
		}

		return re;
	}

	/**
	 * 
	 * 2016年7月21日下午5:59:26
	 * @author mischief7
	 * @param s1
	 * @param s2
	 * @param s
	 * @return
	 */
	public double getArc(Site s1, Site s2, Site s) {
		double tan1 = 0.0;
		double tan2 = 0.0;
		tan1 = (s.getLatitude() - s1.getLatitude())
				/ (s.getLongitude() - s1.getLongitude());
		tan2 = (s.getLatitude() - s2.getLatitude())
				/ (s.getLongitude() - s2.getLongitude());
//		System.out.println("tan1="+tan1+" tan2="+tan2+" atan1="+Math.atan(tan1)+" atan2="+Math.atan(tan2));
		return Math.abs(Math.atan(tan1)-Math.atan(tan2));
	}
}
