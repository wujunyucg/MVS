package edu.swjtu.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.model.Admin;
import edu.swjtu.model.User;

/**
 * δ���
 * 2016��6��7������6:33:16
 * @author jimolonely
 * TODO
 */
public interface UserDao {
	/**
	 * ��½��֤
	 * 2016��6��7������6:48:34
	 * @author jimolonely
	 * @param user
	 * @param con
	 * @return ʧ���򷵻�null
	 */
	public User login(User user,Connection con);
	/**
	 * ����һ���û�
	 * 2016��6��7������6:47:44
	 * @author jimolonely
	 * @param user
	 * @param con
	 * @return ������Ӱ�������
	 * @throws SQLException
	 */
	public int addUser(User user,Connection con) throws SQLException;
	/**
	 * 
	 * 2016年7月9日上午10:14:04
	 * @author mischief7
	 * @param userId
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public int deleteUser(int userId,Connection con) throws SQLException;
	/**
	 * 
	 * 2016年7月9日上午10:14:13
	 * @author mischief7
	 * @param user
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public int updateUser(User user,Connection con) throws SQLException;
	/**
	 * 
	 * 2016年7月9日上午10:15:58
	 * @author mischief7
	 * @param userId
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public User getUserByNumber(String userNumber,Connection con) throws SQLException;
	public User getUserById(int userId,Connection con) throws SQLException;
	/**
	 * 
	 * 2016年7月9日上午10:16:02
	 * @author mischief7
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public ArrayList getAllUser(Connection con) throws SQLException;
	
}
