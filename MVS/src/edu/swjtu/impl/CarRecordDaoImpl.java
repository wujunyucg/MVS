package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.CarRecordDao;
import edu.swjtu.model.CarRecord;

public class CarRecordDaoImpl implements CarRecordDao {
	
	private CarRecord getCarRecordOne(ResultSet rs) throws SQLException{
		CarRecord carrec = new CarRecord();
		carrec.setCarrecordId(rs.getInt("carRecord_id"));
		carrec.setCarId(rs.getInt("carRecord_carId"));
		carrec.setDate(rs.getString("carRecord_date"));
		carrec.setNum(rs.getInt("carRecord_num"));
		carrec.setRate(rs.getDouble("carRecord_rate"));
		carrec.setStaffIds(rs.getString("carRecord_staffIds"));
		return carrec;
	}
	
	@Override
	public int addCarRecord(CarRecord carrecord, Connection con)
			throws SQLException {
		String sql = "insert into carrecord values(null,?,?,?,?,?)";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setInt(1, carrecord.getCarId());
		pstm.setString(2, carrecord.getDate());
		pstm.setInt(3, carrecord.getNum());
		pstm.setDouble(4, carrecord.getRate());
		pstm.setString(5, carrecord.getStaffIds());
		
		return pstm.executeUpdate();
	}

	@Override
	public int deleteCarRecord(int carrecordId, Connection con)
			throws SQLException {
		String sql = "delete  from carrecord where carRecord_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, carrecordId);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int updateCarRecord(CarRecord carrecord, Connection con)
			throws SQLException {
		String sql = "update  carrecord set carRecord_carId = ? ,carRecord_date = ? ,"
				+ "carRecord_num = ? ,carRecord_rate = ? ,carRecord_staffIds = ?"
				+ " where carRecord_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, carrecord.getCarId());
			pstm.setString(2, carrecord.getDate());
			pstm.setInt(3, carrecord.getNum());
			pstm.setDouble(4, carrecord.getRate());
			pstm.setString(5, carrecord.getStaffIds());
			pstm.setInt(6, carrecord.getCarrecordId());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public CarRecord getCarRecordById(int carrecordId, Connection con)
			throws SQLException {
		CarRecord carrec = null;
		String sql = "select * from carrecord where carRecord_id=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, carrecordId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				carrec = getCarRecordOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return carrec;
	}

	@Override
	public ArrayList getAllCarRecord(Connection con) throws SQLException {
		ArrayList<CarRecord> carrecList = new ArrayList<CarRecord>(); 
		String sql = "select * from carrecord";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				carrecList.add(getCarRecordOne(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return carrecList;
	}

}
