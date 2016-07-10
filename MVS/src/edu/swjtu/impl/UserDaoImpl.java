package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.UserDao;
import edu.swjtu.model.Admin;
import edu.swjtu.model.User;
/**
 * 
 * 2016年7月9日上午11:07:00
 * @author mischief
 * TODO
 */
public class UserDaoImpl implements UserDao{
	
	private User getUserOne(ResultSet rs) throws SQLException{
		User user = new User();
		user.setUserId(rs.getInt("user_id"));
		user.setNumber(rs.getString("user_number"));
		user.setPassword(rs.getString("user_password"));
		user.setAdminId(rs.getInt("user_adminId"));
		user.setType(rs.getInt("user_type"));
		return user;
	}
	
	@Override
	public User login(User user, Connection con) {
		
		User ru = null;
		String sql = "select * from user where user_number=? and user_password=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, user.getNumber());
			pstm.setString(2, user.getPassword());
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				ru = new User();
				ru.setNumber(rs.getString("user_number"));
				ru.setPassword(rs.getString("user_password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ru;
	}

	@Override
	public int addUser(User user, Connection con) throws SQLException {
		
		String sql = "insert into user values(null,?,?,?,?)";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, user.getNumber());
		pstm.setString(2, user.getPassword());
		pstm.setInt(3, user.getAdminId());
		pstm.setInt(4, user.getType());
		
		return pstm.executeUpdate();
	}

	@Override
	public int deleteUser(int userId, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete  from user where user_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, userId);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int updateUser(User user, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update  user set user_number = ? ,user_password = ? ,user_adminId = ? ,user_type = ? where user_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, user.getNumber());
			pstm.setString(2, user.getPassword());
			pstm.setInt(3, user.getAdminId());
			pstm.setInt(4, user.getType());
			pstm.setInt(5, user.getUserId());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public User getUserById(int userId, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		User user = null;
		String sql = "select * from user where user_id=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, userId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				user = getUserOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	@Override
	public ArrayList getAllUser(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<User> userList = new ArrayList<User>(); 
		String sql = "select * from user";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				userList.add(getUserOne(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return userList;
	}
	
	@Override
	public User getUserByNumber(String userNumber, Connection con)
			throws SQLException {
		User user = null;
		String sql = "select * from user where user_number=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, userNumber);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				user = getUserOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	@Override
	public User getUserByNumber(String userNumber, Connection con)
			throws SQLException {
		User user = null;
		String sql = "select * from user where user_number=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, userNumber);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				user = getUserOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

}
