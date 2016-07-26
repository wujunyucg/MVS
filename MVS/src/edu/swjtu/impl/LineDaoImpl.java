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
		line.setCarId(rs.getString("line_carId"));
		line.setName(rs.getString("line_name"));
		line.setNum(rs.getInt("line_num"));
		line.setSiteId(rs.getString("line_siteId"));
		line.setRate(rs.getDouble("line_rate"));
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
				+ "line_carId=?,line_name=? line_rate = ? where line_id = ?";
		// String sql = "update line  set  where line_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, line.getLineId());
			pstm.setString(2, line.getSiteId());
			pstm.setInt(3, line.getNum());
			pstm.setString(4, line.getCarId());
			pstm.setString(5, line.getName());
			pstm.setDouble(6, line.getRate());
			pstm.setInt(7, line.getLineId());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int addLine(Line line, Connection con) throws SQLException {
		String sql = "insert into line values(null,?,?,?,?,?)";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, line.getSiteId());
		pstm.setInt(2, line.getNum());
		pstm.setString(3, line.getCarId());
		pstm.setString(4, line.getName());
		pstm.setDouble(5, line.getRate());
		
		return pstm.executeUpdate();
	}

	@Override
	public int addMoreLine(ArrayList<Line> linelist, Connection con)
			throws SQLException {
		String sql = "insert into line values(null,?,?,?,?,?)";
		PreparedStatement pstm = con.prepareStatement(sql);
		int u=0;
		for(int i=0;i<linelist.size();i++){
			pstm.setString(1, linelist.get(i).getSiteId());
			pstm.setInt(2, linelist.get(i).getNum());
			pstm.setString(3, linelist.get(i).getCarId());
			pstm.setString(4, linelist.get(i).getName());
			pstm.setDouble(5, linelist.get(i).getRate());
			u += pstm.executeUpdate();
		}
		return u / linelist.size();
	}

	@Override
	public int deleteLine(int lineId, Connection con) throws SQLException {
		String sql = "delete  from line where line_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, lineId);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}
	
	
	
	public int deleteAllLine(Connection con) throws SQLException {
		String sql = "delete from line";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}
}
