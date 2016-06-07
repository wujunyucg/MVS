package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.swjtu.dao.UserDao;
import edu.swjtu.model.User;

public class UserDaoImpl implements UserDao{

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
		
		String sql = "insert into user values(null,?,?,?)";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, user.getNumber());
		pstm.setString(2, user.getPassword());
		pstm.setInt(3, user.getAdminId());
		
		return pstm.executeUpdate();
	}

}
