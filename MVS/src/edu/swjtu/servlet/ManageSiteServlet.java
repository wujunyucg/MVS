package edu.swjtu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Site;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;
import edu.swjtu.util.KMeans;

/**
 * 
 * ManageSiteServlet.java类
 * 2016年7月25日
 * @author wujunyu
 * TODo
 */
public class ManageSiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ManageSiteServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBUtil db = new DBUtil();
		try {
			Connection con = db.getCon();
			if(request.getAttribute("type").equals("0")){
				ArrayList<Staff> staffList = new ArrayList<Staff>();
				StaffDaoImpl sdi = new StaffDaoImpl();
				staffList = sdi.getAllStaff(new DBUtil().getCon());
				KMeans km = new KMeans(0);
				for(int i=1;;i++){
					km.setK(i);
					km.setDataSet(staffList);
					km.execute();
					ArrayList<ArrayList<Staff>> cluster = km.getCluster();
					ArrayList<double[]> center = km.getCenter();
					int f=0;
					for(int j =0;j<cluster.size();j++){
						for(int k=0;k<cluster.get(j).size();k++){
							if(km.GetDistance(cluster.get(j).get(k).getLati(), cluster.get(j).get(k).getLongti(), center.get(j)[0], center.get(j)[1])>1.5){
								f=1;
								break;
							}
								
						}
						if(f==1)
							break;
					}
					if(f==1 )
						continue;
					for(int j =0;j<cluster.size();j++){
						if(cluster.get(j).size()==0){
							cluster.remove(j);
							center.remove(j);
						}
					}
					ArrayList<Site> siteList = new ArrayList<Site>();
					for(int j =0;j<center.size();j++){
						Site site = new Site();
						site.setLatitude(center.get(j)[0]);
						site.setLongitude(center.get(j)[1]);
						site.setBufftag(j);
						//site.setAddress("chen");
						site.setName(String.valueOf(j+1));
						site.setPeoNum(cluster.get(j).size());
						siteList.add(site);
					}
					/*SiteDaoImpl sdi1 = new SiteDaoImpl();
					sdi1.addListSite(siteList, con);
					siteList = sdi1.getAllSite(con);
					for(Site site:siteList){
						staffList.clear();
						for(int j=0;j<cluster.get(site.getBufftag()).size();j++){					
							Staff staff =new Staff();
							staff = cluster.get(site.getBufftag()).get(j);
							staff.setSiteId(site.getSiteId());
							staffList.add(staff);
						}
						sdi.updateListStaff(staffList, con);
					}
					System.out.print(cluster.size());*/
					request.getSession().setAttribute("buff_site_list", siteList);
					break;
				}
				request.getRequestDispatcher("../jsp_user/site_get_address.jsp").forward(request,response);
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

}
