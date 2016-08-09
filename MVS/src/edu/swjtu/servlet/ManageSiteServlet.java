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

import sun.launcher.resources.launcher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.SiteRecordDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Line;
import edu.swjtu.model.Site;
import edu.swjtu.model.SiteRecord;
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
		  response.setContentType("text/html;charset=UTF-8");
		DBUtil db = new DBUtil();
		PrintWriter out = response.getWriter();
		try {
			Connection con = db.getCon();
			if(request.getParameter("type").equals("0")){
				ArrayList<Staff> staffList = new ArrayList<Staff>();
				StaffDaoImpl sdi = new StaffDaoImpl();
				 SiteDaoImpl sdi1 = new SiteDaoImpl();
	                sdi1.deleteAllSite(con);
				staffList = sdi.getAllStaff(new DBUtil().getCon());
				KMeans km = new KMeans(0);
				for(int i=1;;i++){
					//System.out.println(i);
					km.setK(i);
					km.setDataSet(staffList);
					km.execute();
					ArrayList<ArrayList<Staff>> cluster = km.getCluster();
					ArrayList<double[]> center = km.getCenter();
					int f=0;
					for(int j =0;j<cluster.size();j++){
						for(int k=0;k<cluster.get(j).size();k++){
							if(km.GetDistance(cluster.get(j).get(k).getLati(), cluster.get(j).get(k).getLongti(), center.get(j)[0], center.get(j)[1])>1.0){
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
						site.setAddress("无法匹配到街道");
						site.setName(String.valueOf(j+1));
						site.setPeoNum(cluster.get(j).size());
						siteList.add(site);
					}
					request.getSession().setAttribute("buff_site_list",siteList);
					request.getSession().setAttribute("buff_cluster",cluster);
					JSONObject jsonObject = new JSONObject();  
			        jsonObject.put("bsitelist", siteList);  
			        out.print(jsonObject.toString());
					out.close();
					break;
				}
				//System.out.println(123);
				//response.sendRedirect("../jsp_user/site_get_address.jsp");
				
			}
			else if(request.getParameter("type").equals("1")){
				String json = request.getParameter("jsonlist");
				JSONObject jo = JSONObject.fromObject(json);  
                JSONArray slist = jo.getJSONArray("slist");
                ArrayList<Site> siteList =(ArrayList<Site>) request.getSession().getAttribute("buff_site_list");
                ArrayList<ArrayList<Staff>> cluster =(ArrayList<ArrayList<Staff>>) request.getSession().getAttribute("buff_cluster");
            	//System.out.println(siteList.size());
            	//System.out.println(slist.size());
            	for (int i = 0; i < slist.size(); i++) {  
            		for (int j = 0; j < slist.size() && j!=i; j++){
            			
            		}
                } 
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
                for (int i = 0; i < siteList.size()-1; i++) {  
            		for (int j = siteList.size()-1; j > i; j--){
            			
            			if(siteList.get(i).getAddress().equals(siteList.get(j).getAddress())){
            				siteList.get(i).setPeoNum(siteList.get(i).getPeoNum()+siteList.get(j).getPeoNum());
            				cluster.get(i).addAll(cluster.get(j));
            				siteList.remove(j);
            				cluster.remove(j);
            				
            			}
            		}
                } 
                for (int i = 0; i < siteList.size(); i++) {  
                	siteList.get(i).setBufftag(i);
                }
                SiteDaoImpl sdi1 = new SiteDaoImpl();
				sdi1.addListSite(siteList, con);
				siteList=sdi1.getBuffSite(con);
				ArrayList<Staff> staffList = new ArrayList<Staff>();
				StaffDaoImpl sdi = new StaffDaoImpl();
				for(Site site:siteList){
					staffList.clear();
					for(int j=0;j<cluster.get(site.getBufftag()).size();j++){					
						Staff staff =new Staff();
						staff = cluster.get(site.getBufftag()).get(j);
						staff.setSiteId(site.getSiteId());
						staffList.add(staff);
					//	System.out.println(site.getBufftag());
					//	KMeans km = new KMeans(0);
					//	System.out.println(km.GetDistance(site.getLatitude(),site.getLongitude(),staff.getLati(),staff.getLongti()));
					}
					sdi.updateListStaff(staffList, con);
					site.setBufftag(-1);
				}
				sdi1.updateListSite(siteList, con);
				siteList=sdi1.getAllSite(con);
				staffList = sdi.getAllStaff(con);
				JSONObject jsonObject = new JSONObject();  
		        jsonObject.put("nsitelist", siteList); 
		        jsonObject.put("stafflist", staffList); 
		      //  System.out.println(jsonObject.toString());
		      
		        out.write(jsonObject.toString());
				out.close();
			}
			else if(request.getParameter("type").equals("2")){
				String json=request.getParameter("json");
				SiteDaoImpl sdi = new SiteDaoImpl();
				JSONObject jo = JSONObject.fromObject(json);
				Site site = sdi.getSiteById(jo.getInt("siteId"), con);
				site.setAddress(jo.getString("address"));
				site.setDelay((int)jo.get("delay"));
				site.setLatitude(jo.getDouble("latitude"));
				site.setLongitude(jo.getDouble("longitude"));
				site.setName(jo.getString("name"));
	
				sdi.updateSite(site, con);
				ArrayList<Site> siteList = sdi.getAllSite(con);
				 JSONObject jsonObject = new JSONObject();  
			        jsonObject.put("sitelist", siteList);
			        System.out.println(jsonObject.toString());
			        out.print(jsonObject.toString());
					out.close();
			}
			else if(request.getParameter("type").equals("3")){
				
				String id=request.getParameter("sizeId");
				//System.out.println(id);
				Site site =new Site();
				site.setSiteId(Integer.parseInt(id));
				SiteDaoImpl sdi = new SiteDaoImpl();
				site = sdi.getSiteById(site.getSiteId(), con);
				String lineId = site.getLineId();
				if(lineId==null||lineId.equals("")){
					
				}
				else{
					String [] ids = lineId.split(",");
					LineDaoImpl ldi = new LineDaoImpl();
					for(int i=0;i<ids.length;i++){
						int lid = Integer.parseInt(ids[i]);
						Line line = ldi.getLineById(con, lid);
						String [] sids = line.getSiteId().split(",");
						ArrayList<String> sidList = new ArrayList<String>();
						for(int j=0;j<sids.length;j++){
							sidList.add(sids[j]);
						}
						for(int j=0;j<sidList.size();j++){
							if(sidList.get(j).equals(id)){
								for(int k=j+1;k<sidList.size();k++){
									Site site1= sdi.getSiteById(Integer.parseInt(sidList.get(k)), con);
									String [] lineid1 = site1.getLineId().split(",");
									String [] orders =site1.getOrder().split(",");
									ArrayList<String> orderlist = new ArrayList<String>();
									for(int x=0;x<orders.length;x++){
										orderlist.add(orders[x]);
									}
									for(int l=0;l<lineid1.length;l++){
										if(lineid1[l].equals(ids[i])){
											System.out.println(lineid1.length);
											System.out.println(orders.length);
											orderlist.set(l, String.valueOf(Integer.parseInt(orders[l])-1));
											String order = orderlist.toString().replace("[", "").replace("]", "").replace(" ", "");
											site1.setOrder(order);
											sdi.updateSite(site1, con);
											break;
										}
									}
									
								}
								sidList.remove(j);
								j--;
							}
							
						}
						String slist = sidList.toString().replace("[", "").replace("]", "").replace(" ", "");
						line.setSiteId(slist);
						ldi.updateLine(con, line);
						
					}
				}
				SiteRecordDaoImpl srdi= new SiteRecordDaoImpl();
				srdi.deleteSiteRecordBySiteId(site.getSiteId(), con);
				sdi.deleteOneSite(site, con);
				StaffDaoImpl sdi1= new StaffDaoImpl();
				ArrayList<Staff> staffList = new ArrayList<Staff>();
				staffList=sdi1.getStaffBySiteId(site.getSiteId(), con);
				for(int i=0;i<staffList.size();i++){
					staffList.get(i).setSiteId(-1);;
				 }
				sdi1.updateListStaff(staffList, con);
				 ArrayList<Site> siteList = sdi.getAllSite(con);
				 for(int i=0;i<siteList.size();i++){
					 siteList.get(i).setName(String.valueOf(i));;
				 }
				 sdi.updateListSite(siteList, con);
				  siteList = sdi.getAllSite(con);
				  staffList=sdi1.getAllStaff(con);
				 JSONObject jsonObject = new JSONObject();  
			        jsonObject.put("sitelist", siteList); 
			        jsonObject.put("stafflist", staffList); 
			        out.write(jsonObject.toString());
					out.close();
			}
			else if(request.getParameter("type").equals("4")){
				
				String json=request.getParameter("json");
				JSONObject jo = JSONObject.fromObject(json);
				Site site =new Site();
				site.setAddress(jo.getString("address"));
				site.setBufftag(0);
				site.setDelay(5);
				site.setLatitude(jo.getDouble("latitude"));
				site.setLongitude(jo.getDouble("longitude"));			
				site.setName(jo.getString("name"));
				site.setPeoNum(jo.getInt("peoNum"));
				site.setSiteId(0);
				SiteDaoImpl sdi = new SiteDaoImpl();
				sdi.addOneSite(site, con);
				ArrayList<Site> siteList = sdi.getAllSite(con);
				 JSONObject jsonObject = new JSONObject();  
			        jsonObject.put("sitelist", siteList); 
			        out.write(jsonObject.toString());
					out.close();
			}
			else if(request.getParameter("type").equals("5")){
				
				String name=request.getParameter("sitename");
			//	System.out.println(name);
				SiteDaoImpl sdi = new SiteDaoImpl();
				ArrayList<Site> siteList = sdi.getListSiteByAddress(name, con);
				 JSONObject jsonObject = new JSONObject();  
			        jsonObject.put("csitelist", siteList); 
			        out.write(jsonObject.toString());
					out.close();
			}
			else if(request.getParameter("type").equals("6")){
				
				String lineid=request.getParameter("lineid");
			//	System.out.println(name);
				LineDaoImpl ldi = new LineDaoImpl();
				SiteDaoImpl sdi = new SiteDaoImpl();
				ArrayList<Site> siteList = new ArrayList<Site>();
				Line line = ldi.getLineById(con, Integer.parseInt(lineid));
				String [] siteids =line.getSiteId().split(",");
				for(int i=0;i<siteids.length;i++){
					Site site = sdi.getSiteById(Integer.parseInt(siteids[i]), con);
					siteList.add(site);
				}
				 JSONObject jsonObject = new JSONObject();  
			        jsonObject.put("lsitelist", siteList); 
			        out.write(jsonObject.toString());
					out.close();
			}
			else if(request.getParameter("type").equals("7")){
				ArrayList<Staff> staffList = new ArrayList<Staff>();
				StaffDaoImpl sdi = new StaffDaoImpl();
				staffList = sdi.getNoSiteStaff(new DBUtil().getCon());
				System.out.println(staffList.size());
				KMeans km = new KMeans(0);
				for(int i=1;;i++){
					//System.out.println(i);
					km.setK(i);
					km.setDataSet(staffList);
					km.execute();
					ArrayList<ArrayList<Staff>> cluster = km.getCluster();
					ArrayList<double[]> center = km.getCenter();
					int f=0;
					for(int j =0;j<cluster.size();j++){
						for(int k=0;k<cluster.get(j).size();k++){
							if(km.GetDistance(cluster.get(j).get(k).getLati(), cluster.get(j).get(k).getLongti(), center.get(j)[0], center.get(j)[1])>1.0){
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
					System.out.println(center.size());
					ArrayList<Site> siteList = new ArrayList<Site>();
					DecimalFormat df = new DecimalFormat( "0.000000");  
					for(int j =0;j<center.size();j++){
						Site site = new Site();
						site.setLatitude(Double.valueOf(df.format(center.get(j)[0])));
						site.setLongitude(Double.valueOf(df.format(center.get(j)[1])));
						site.setBufftag(j);
						site.setAddress("无法匹配到街道");
						site.setName(String.valueOf(j+1));
						site.setPeoNum(cluster.get(j).size());
						siteList.add(site);
					}
					request.getSession().setAttribute("buff_site_list",siteList);
					request.getSession().setAttribute("buff_cluster",cluster);
					JSONObject jsonObject = new JSONObject();  
			        jsonObject.put("bsitelist", siteList);  
			        out.print(jsonObject.toString());
					out.close();
					break;
				}
				//System.out.println(123);
				//response.sendRedirect("../jsp_user/site_get_address.jsp");
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

}
