package edu.swjtu.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.swjtu.model.Admin;

/**
 * 
 * AdminDao.java类
 * 2016年6月20日
 * @author wujunyu
 * TODo
 */
public interface AdminDao {
	/**
	 * 
	 * 2016年6月20日下午8:52:18
	 * @author wujunyu
	 * TODO 增加角色
	 * @param admin
	 * @param con
	 * @return int
	 * @throws SQLException
	 */
	public int addAdmin(Admin admin,Connection con) throws SQLException;
	/**
	 * 
	 * 2016年6月20日下午8:53:12
	 * @author wujunyu
	 * TODO  根据adminId删除角色
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public int deleteAdmin(int adminId,Connection con) throws SQLException;
	/**
	 * 
	 * 2016年6月20日下午8:56:12
	 * @author wujunyu
	 * TODO		更新角色
	 * @param adminId
	 * @param admnin
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public int updateAdmin(Admin admin,Connection con) throws SQLException;
	/**
	 * 
	 * 2016年6月20日下午8:56:56
	 * @author wujunyu
	 * TODO		根据adminId得到角色
	 * @param id
	 * @param con
	 * @return	
	 * @throws SQLException
	 */
	public Admin getAdminById(int adminId,Connection con) throws SQLException;
	/**
	 * 
	 * 2016年6月20日下午8:57:25
	 * @author wujunyu
	 * TODO	得到所有角色信息，返回列表
	 * @param con
	 * @return list
	 * @throws SQLException
	 */
	public ArrayList getAllAdmin(Connection con) throws SQLException;
}
