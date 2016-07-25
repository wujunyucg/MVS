package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.SynchDao;
import edu.swjtu.model.BuffStaff;
import edu.swjtu.model.Staff;
import edu.swjtu.model.Synch;

public class SynchDaoImpl implements SynchDao {

	private Synch getSynchOne(ResultSet rs) throws SQLException{
		Synch synch = new Synch();
		synch.setId(rs.getInt("synch_id"));
		synch.setName(rs.getString("synch_name"));
		synch.setTime(rs.getString("synch_Time"));
		return synch;
	}
	
	@Override
	public int addSynch(Synch synch, Connection con) {
		String sql = "insert into synch (synch_id,synch_name,synch_time,synch_flag) values (? , ?,?,?)";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1,synch.getId());
			pstm.setString(2,synch.getName());
			pstm.setString(3,synch.getTime());
			pstm.setInt(4,synch.getFlag());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public ArrayList<Synch> getSynch(int startPage,int pageNum,Connection con) {
		ArrayList<Synch> synchList = new ArrayList<Synch>();
		String sql = "select * from synch order by synch_id desc limit ?,?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, (startPage-1)*pageNum);
			pstm.setInt(2, pageNum);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				synchList.add(getSynchOne(rs)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return synchList;
	}
	
	@Override
	public int getSynchNum(Connection con) {
		String sql = "select count(*) from synch";
		PreparedStatement pstm;
		try {
			pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			rs.next();//移到第一条数据
			int sum = rs.getInt(1);
			return sum;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	@Override
	public Synch getLastSynch(Connection con) {
		Synch synch = new Synch();
		String sql = "select * from staff  order by synch_id desc limit 1";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				synch = getSynchOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return synch;
	}

}
