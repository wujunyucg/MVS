package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.swjtu.dao.AdminDao;
import edu.swjtu.model.Admin;
import edu.swjtu.model.User;

public class AdminDaoImpl implements AdminDao {
	
	/**
	 * 
	 * 2016��6��20������9:19:38
	 * @author wujunyu
	 * TODO ���ô���ݿ�õ��Ľ�ɫ
	 * @return 
	 * @throws SQLException 
	 */
	private Admin getAdminOne(ResultSet rs) throws SQLException{
		Admin admin = new Admin();
		admin.setAdminId(rs.getInt("admin_id"));
		admin.setName(rs.getString("admin_name"));
		admin.setPowerId(rs.getString("admin_powerid"));
		return admin;
	}
	
	@Override
	public int addAdmin(Admin admin, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into admin (admin_powerId,admin_name) values (? , ?)";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, admin.getPowerId());
			pstm.setString(2, admin.getName());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int deleteAdmin(int adminId, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "delete  from admin where admin_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, adminId);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int updateAdmin( Admin admin, Connection con)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update  admin set admin_powerId = ? ,admin_name = ? where admin_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, admin.getPowerId());
			pstm.setString(2, admin.getName());
			pstm.setInt(3, admin.getAdminId());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public Admin getAdminById(int adminId, Connection con) throws SQLException {
		Admin admin = null;
		String sql = "select * from admin where admin_id=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, adminId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				admin = getAdminOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return admin;
	}

	@Override
	public ArrayList<Admin> getAllAdmin(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Admin> adminList = new ArrayList<Admin>(); 
		String sql = "select * from admin";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				adminList.add(getAdminOne(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return adminList;
		
	}

	@Override
	public Admin getAdminByName(String name, Connection con)
			throws SQLException {
		Admin admin = null;
		String sql = "select * from admin where admin_name=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				admin = getAdminOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return admin;
	}

	@Override
	public ArrayList<Admin> getPageAdmin(Connection con, int startPage,
			int pageNum) throws SQLException {
		String sql = "select *from admin limit "+((startPage-1)*pageNum+1)+","+pageNum;
		ArrayList<Admin> re = new ArrayList<Admin>();
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()){
			re.add(getAdminOne(rs));
		}
		return re;
	}

	@Override
	public int getTotal(Connection con) throws SQLException {
		String sql = "select count(*) from admin";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		rs.next();//移到第一条数据
		int sum = rs.getInt(1);
		return sum;
	}

}
