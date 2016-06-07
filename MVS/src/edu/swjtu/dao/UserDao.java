package edu.swjtu.dao;
import java.sql.Connection;
import java.sql.SQLException;

import edu.swjtu.model.User;

/**
 * 未完成
 * 2016年6月7日下午6:33:16
 * @author jimolonely
 * TODO
 */
public interface UserDao {
	/**
	 * 登陆验证
	 * 2016年6月7日下午6:48:34
	 * @author jimolonely
	 * @param user
	 * @param con
	 * @return 失败则返回null
	 */
	public User login(User user,Connection con);
	/**
	 * 增加一个用户
	 * 2016年6月7日下午6:47:44
	 * @author jimolonely
	 * @param user
	 * @param con
	 * @return 返回受影响的行数
	 * @throws SQLException
	 */
	public int addUser(User user,Connection con) throws SQLException;
}
