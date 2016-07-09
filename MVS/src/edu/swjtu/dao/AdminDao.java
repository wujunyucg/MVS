package edu.swjtu.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import edu.swjtu.model.Admin;

/**
 * 
 * AdminDao.java
 * 2016 6 20 
 * @author wujunyu
 * TODo
 */
public interface AdminDao {
	/**
	 * 
	 * 2016 6 20  8:52:18
	 * @author wujunyu
	 * TODO 添加一个管理员
	 * @param admin
	 * @param con
	 * @return int
	 * @throws SQLException
	 */
	public int addAdmin(Admin admin,Connection con) throws SQLException;
	/**
	 * 
	 * 2016 6 20 8:53:12
	 * @author wujunyu
	 * TODO  根据adminId删除一个管理员
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public int deleteAdmin(int adminId,Connection con) throws SQLException;
	/**
	 * 
	 * 2016年7月9日上午9:50:35
	 * @author wujunyu
	 * TODO 更新一个管理员
	 * @param admin
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public int updateAdmin(Admin admin,Connection con) throws SQLException;
	/**
	 * 
	 * 2016年7月9日上午9:51:07
	 * @author wujunyu
	 * TODO 根据adminID查询一个管理员
	 * @param adminId
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public Admin getAdminById(int adminId,Connection con) throws SQLException;
	/**
	 * 
	 * 2016年7月9日上午9:51:50
	 * @author wujunyu
	 * TODO 得到所有管理员角色
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Admin> getAllAdmin(Connection con) throws SQLException;
}
