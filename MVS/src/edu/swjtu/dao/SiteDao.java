package edu.swjtu.dao;

import java.sql.Connection;
import java.util.ArrayList;

import edu.swjtu.model.Site;

/**
 * 
 * SiteDao.java类
 * 2016年7月9日
 * @author wujunyu
 * TODo
 */
public interface SiteDao {
	/**
	 * 
	 * 2016年7月9日下午1:46:02
	 * @author wujunyu
	 * TODO 添加一个站点
	 * @param site
	 * @param con
	 * @return
	 */
	public int  addOneSite(Site site,Connection con);
	/**
	 * 
	 * 2016年7月9日下午1:46:21
	 * @author wujunyu
	 * TODO 添加一个列表的站点
	 * @param siteList
	 * @param con
	 * @return
	 */
	public int  addListSite(ArrayList<Site> siteList,Connection con);
	/**
	 * 
	 * 2016年7月9日下午1:46:39
	 * @author wujunyu
	 * TODO 删除一个站点
	 * @param site
	 * @param con
	 * @return
	 */
	public int deleteOneSite(Site site,Connection con);
	/**
	 * 
	 * 2016年7月9日下午1:46:52
	 * @author wujunyu
	 * TODO 删除一个列表的站点
	 * @param siteList
	 * @param con
	 * @return
	 */
	public int  deleteListSite(ArrayList<Site> siteList,Connection con);
	/**
	 * 
	 * 2016年7月9日下午1:47:16
	 * @author wujunyu
	 * TODO 更新一个站点
	 * @param site
	 * @param con
	 * @return
	 */
	public int updateSite(Site site,Connection con);
	/**
	 * 
	 * 2016年7月9日下午1:47:35
	 * @author wujunyu
	 * TODO 根据站点id得到一个站点
	 * @param siteId
	 * @param con
	 * @return
	 */
	public Site getSiteById(int siteId,Connection con);
	/**
	 * 根据站点 名称得到站点
	 * 2016年7月9日下午1:47:53
	 * @author wujunyu
	 * TODO
	 * @param name
	 * @param con
	 * @return
	 */
	public Site getSiteByName(String name,Connection con);
	/**
	 * 
	 * 2016年7月9日下午1:48:08
	 * @author wujunyu
	 * TODO 根据线路id得到一个站点
	 * @param lineId
	 * @param con
	 * @return
	 */
	public ArrayList<Site> getSiteByLineId(int lineId,Connection con);
	ArrayList<Site> getAllSite(Connection con);
}
