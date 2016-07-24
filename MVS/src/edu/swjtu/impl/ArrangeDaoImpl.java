package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.ArrangeDao;
import edu.swjtu.model.Arrange;
import edu.swjtu.model.Car;

public class ArrangeDaoImpl implements ArrangeDao {

	private Arrange getOneArrange(ResultSet rs) throws SQLException {
		Arrange arr = new Arrange();
		arr.setArrangeId(rs.getInt("arrange_id"));
		arr.setName(rs.getString("arrange_name"));
		arr.setLineId(rs.getInt("arrange_lineId"));
		arr.setCarId(rs.getInt("arrange_carId"));
		arr.setTime(rs.getString("arrange_time"));
		arr.setDate(rs.getString("arrange_date"));
		return arr;
	}

	@Override
	public ArrayList<Arrange> getAllArrange(Connection con) throws SQLException {
		ArrayList<Arrange> list = new ArrayList<Arrange>();
		String sql = "select * from arrange";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			list.add(getOneArrange(rs));
		}
		return list;
	}

	@Override
	public ArrayList<Arrange> getPageArrange(Connection con, int startPage,
			int pageNum) throws SQLException {
		ArrayList<Arrange> list = new ArrayList<Arrange>();
		String sql = "select *from arrange limit "
				+ ((startPage - 1) * pageNum) + "," + pageNum;
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			list.add(getOneArrange(rs));
		}
		return list;
	}

	@Override
	public int getTotal(Connection con) throws SQLException {
		String sql = "select count(*) from arrange";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		rs.next();// 移到第一条数据
		int sum = rs.getInt(1);
		return sum;
	}

	@Override
	public ArrayList<Arrange> getPageMonthArr(Connection con, int startPage,
			int pageNum, String date) throws SQLException {
		ArrayList<Arrange> list = new ArrayList<Arrange>();
		String sql = "select *from arrange where arrange_date like '%"
				+ date + "%' limit " + ((startPage - 1) * pageNum) + "," + pageNum;
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			list.add(getOneArrange(rs));
		}
		return list;
	}

	@Override
	public int getTotalByMonth(Connection con, String date) throws SQLException {
		String sql = "select count(*) from arrange where arrange_date like '%"
				+ date + "%'";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		rs.next();// 移到第一条数据
		int sum = rs.getInt(1);
		return sum;
	}

	@Override
	public int delArrById(Connection con, int id) throws SQLException {
		String sql = "delete  from arrange where arrange_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public ArrayList<Arrange> getAllMonthArr(Connection con, String date)
			throws SQLException {
		ArrayList<Arrange> list = new ArrayList<Arrange>();
		String sql = "select *from arrange where arrange_date like '%"
				+ date + "%'";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			list.add(getOneArrange(rs));
		}
		return list;
	}

	@Override
	public String getArrNameById(int arrId, Connection con) throws SQLException {
		Arrange arr  = null;
		String sql = "select * from arrange where arrange_id=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, arrId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				arr = getOneArrange(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(arr == null){
			return "-1";
		}else{
			return arr.getName();
		}
	}
	
	@Override
	public int addArr(Arrange arr, Connection con) throws SQLException {
		String sql = "insert into arrange (arrange_name,arrange_lineId,"
				+ "arrange_carId,arrange_time,arrange_date) values (?,?,?,?, ?)";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			//pstm.setInt(1, arr.getArrangeId());
			pstm.setString(1, arr.getName());
			pstm.setInt(2, arr.getLineId());
			pstm.setInt(3, arr.getCarId());
			pstm.setString(4, arr.getTime());
			pstm.setString(5, arr.getDate());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public ArrayList<Arrange> getAllArrByDate(Connection con, String date)
			throws SQLException {
		ArrayList<Arrange> list = new ArrayList<Arrange>();
		String sql = "select *from arrange where arrange_date = ?";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, date);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()){
			list.add(getOneArrange(rs));
		}
		return list;
	}
}
