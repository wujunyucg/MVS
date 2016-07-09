package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.PowerDao;
import edu.swjtu.model.Power;
import edu.swjtu.model.User;

public class PowerDaoImpl implements PowerDao {

	private Power getPowerOne(ResultSet rs) throws SQLException{
		Power power = new Power();
		power.setPowerId(rs.getInt("power_id"));
		power.setContent(rs.getString("power_content"));
		return power;
	}
	
	@Override
	public int addPower(Power power, Connection con) throws SQLException {
		String sql = "insert into power values(null,?)";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, power.getContent());
		
		return pstm.executeUpdate();
	}

	@Override
	public int deletePower(int powerId, Connection con) throws SQLException {
		String sql = "delete  from power where power_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, powerId);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int updatePower(Power power, Connection con) throws SQLException {
		String sql = "update  power set power_content = ? where power_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, power.getContent());
			pstm.setInt(2, power.getPowerId());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public Power getPowerById(int powerId, Connection con) throws SQLException {
		Power power = null;
		String sql = "select * from power where power_id=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, powerId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				power = getPowerOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return power;
	}

	@Override
	public ArrayList getAllPower(Connection con) throws SQLException {
		ArrayList<Power> powerList = new ArrayList<Power>(); 
		String sql = "select * from power";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				powerList.add(getPowerOne(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return powerList;
	}

}
