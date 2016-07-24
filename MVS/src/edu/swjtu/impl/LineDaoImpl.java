package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.LineDao;
import edu.swjtu.model.Car;
import edu.swjtu.model.Line;

public class LineDaoImpl implements LineDao {

	private Line getOneLine(ResultSet rs) throws SQLException {
		Line line = new Line();
		line.setLineId(rs.getInt("line_id"));
		line.setCarId(rs.getInt("line_carId"));
		line.setName(rs.getString("line_name"));
		line.setNum(rs.getInt("line_num"));
		line.setSiteId(rs.getString("line_siteId"));
		return line;
	}

	@Override
	public Line getLineById(Connection con, int id) throws SQLException {
		String sql = "select*from line where line_id = ?";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		Line line = null;
		if (rs.next()) {
			line = getOneLine(rs);
		}
		return line;
	}

	@Override
	public Line getLineByName(Connection con, String name) throws SQLException {
		Line line = null;
		String sql = "select * from line where line_name=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				line = getOneLine(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return line;
	}

	@Override
	public ArrayList<Line> getAllLine(Connection con) throws SQLException {
		ArrayList<Line> list = new ArrayList<Line>();
		String sql = "select * from line";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(getOneLine(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public int updateLine(Connection con, Line line) throws SQLException {
		String sql = "update line set line_id = ?,line_siteId=?,line_num=?,"
				+ "line_carId=?,line_name=? where line_id = ?";
		// String sql = "update line  set  where line_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, line.getLineId());
			pstm.setString(2, line.getSiteId());
			pstm.setInt(3, line.getNum());
			pstm.setInt(4, line.getCarId());
			pstm.setString(5, line.getName());
			pstm.setInt(6, line.getLineId());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

}
