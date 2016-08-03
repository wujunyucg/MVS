package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.LostDao;
import edu.swjtu.model.Arrange;
import edu.swjtu.model.Lost;

public class LostDaoImpl implements LostDao{

	private Lost getOneLostByRS(ResultSet rs) throws SQLException{
		Lost lost = new Lost();
		lost.setContent(rs.getString("lost_content"));
		lost.setName(rs.getString("lost_name"));
		lost.setDate(rs.getString("lost_date"));
		lost.setLostId(rs.getInt("lost_id"));
		lost.setContact(rs.getString("lost_contact"));
		lost.setType(rs.getString("lost_type"));
		return lost;
	}
	
	@Override
	public ArrayList<Lost> getAllLost(Connection con) {
		String sql = "select * from lost";
		
		return null;
	}

	@Override
	public int addOneLost(Connection con, Lost lost) throws SQLException {
		String sql = "insert into lost (lost_content,lost_name,"
				+ "lost_date,lost_contact,lost_type) values(?,?,?,?,?)";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, lost.getContent());
		pstm.setString(2, lost.getName());
		pstm.setString(3, lost.getDate());
		pstm.setString(4, lost.getContact());
		pstm.setString(5, lost.getType());
		return pstm.executeUpdate();
	}

	@Override
	public ArrayList<Lost> getPageLost(Connection con, int startPage,
			int pageNum) throws SQLException {
		ArrayList<Lost> list = new ArrayList<Lost>();
		String sql = "select *from lost order by lost_date desc limit "
				+ ((startPage - 1) * pageNum) + "," + pageNum;
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			list.add(getOneLostByRS(rs));
		}
		return list;
	}

	@Override
	public ArrayList<Lost> getPageLost(Connection con, int startPage,
			int pageNum, String type) throws SQLException {
		ArrayList<Lost> list = new ArrayList<Lost>();
		String sql = "select *from lost where lost_type = ? order by lost_date desc limit "
				+ ((startPage - 1) * pageNum) + "," + pageNum;
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, type);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			list.add(getOneLostByRS(rs));
		}
		return list;
	}

}
