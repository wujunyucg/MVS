package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.swjtu.dao.LineDao;
import edu.swjtu.model.Line;

public class LineDaoImpl implements LineDao{

	private Line getOneLine(ResultSet rs) throws SQLException{
		Line line = new Line();
		line.setLineId(rs.getInt("line_id"));
		line.setCarId(rs.getInt("line_carId"));
		line.setName(rs.getString("line_name"));
		line.setNum(rs.getInt("line_num"));
		line.setSiteId(rs.getInt("line_siteId"));
		return line;
	}
	@Override
	public Line getLineById(Connection con, int id) throws SQLException {
		String sql = "select*from line where line_id = ?";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		Line line = null;
		if(rs.next()){
			line = getOneLine(rs);
		}
		return line;
	}

}
