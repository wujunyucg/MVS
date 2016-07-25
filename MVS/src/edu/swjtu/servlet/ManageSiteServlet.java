package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
		PrintWriter out = response.getWriter();
		try {
			Connection con = db.getCon();
			if(request.getParameter("type").equals("0")){
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
					DecimalFormat df = new DecimalFormat( "0.000000");  
					for(int j =0;j<center.size();j++){
						Site site = new Site();
						site.setLatitude(Double.valueOf(df.format(center.get(j)[0])));
						site.setLongitude(Double.valueOf(df.format(center.get(j)[1])));
						site.setBufftag(j);
						site.setAddress("chen");
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
				//System.out.println(123);
				//response.sendRedirect("../jsp_user/site_get_address.jsp");
				out.print(1);
			}
			else if(request.getParameter("type").equals("1")){
			System.out.print(2);
				String json = request.getParameter("jsonlist");
				JSONObject jo = JSONObject.fromObject(json);  
                JSONArray slist = jo.getJSONArray("slist");
                ArrayList<Site> siteList =(ArrayList<Site>) request.getSession().getAttribute("buff_site_list");
            	System.out.println(siteList.size());
            	System.out.println(slist.size());
                for(Site site:siteList){
                	for (int i = 0; i < slist.size(); i++) {  
                        JSONObject slistob = JSONObject.fromObject(slist.get(i));
                        if(site.getBufftag()==(int)slistob.get("index")){
                        	 if((String)slistob.get("address")==null || ((String)slistob.get("address")).equals("") )
                             	siteList.get(i).setAddress("未匹配到街道");
                             else
                             	siteList.get(i).setAddress((String)slistob.get("address")); 
                             siteList.get(i).setLatitude((double)slistob.get("lati")); 
                             siteList.get(i).setLongitude((double)slistob.get("long")); 
                             break;
                        }
                    } 
                }
                
                SiteDaoImpl sdi1 = new SiteDaoImpl();
				sdi1.addListSite(siteList, con);
				out.print(1);
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

}
