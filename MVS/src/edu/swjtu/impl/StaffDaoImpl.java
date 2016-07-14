package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.StaffDao;
import edu.swjtu.model.Staff;

public class StaffDaoImpl implements StaffDao {

	private Staff getStaffOne(ResultSet rs) throws SQLException{
		Staff staff = new Staff();
		staff.setAddress(rs.getString("staff_address"));
		staff.setArrangeId(rs.getInt("staff_arrangeId"));
		staff.setDepartment(rs.getString("staff_department"));
		staff.setGroup(rs.getString("staff_group"));
		staff.setLineId(rs.getInt("staff_lineId"));
		staff.setName(rs.getString("staff_name"));
		staff.setNumber(rs.getString("staff_number"));
		staff.setSiteId(rs.getInt("staff_siteId"));
		staff.setStaffId(rs.getInt("staff_id"));
		return staff;
	}
	
	private void getPreSta(PreparedStatement ps, Staff staff) throws SQLException{
		
		ps.setInt(1,staff.getStaffId());
		ps.setString(2,staff.getName());
		ps.setString(3,staff.getNumber());
		ps.setString(4,staff.getDepartment());
		ps.setString(5,staff.getGroup());
		ps.setInt(6,staff.getArrangeId());
		ps.setString(7,staff.getAddress());
		ps.setInt(8,staff.getLineId());
		ps.setInt(9,staff.getSiteId());
	}
	@Override
	public int addOneStaff(Staff staff, Connection con) {
		// TODO Auto-generated method stub
		String sql = "insert into staff (staff_id,staff_name,staff_number,staff_department,staff_group,staff_arrangeId,staff_address,staff_lineId,staff_siteId) values (? , ?,?,?,?,?,?,?,?)";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			getPreSta(pstm,staff);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int addListStaff(ArrayList<Staff> staffList, Connection con) {
		// TODO Auto-generated method stub
		Staff staff = new Staff();
		String sql = "insert into staff (staff_id,staff_name,staff_number,staff_department,staff_group,staff_arrangeId,staff_address,staff_lineId,staff_siteId) values (? , ?,?,?,?,?,?,?,?)";
		int [] rs = null;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			for(int i = 0; i<staffList.size(); i++){
				staff = staffList.get(i);
				getPreSta(pstm,staff);
				pstm.addBatch();
			}
			rs = pstm.executeBatch();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs[0];
	}

	@Override
	public int deleteOneStaff(int staffId, Connection con) {
		// TODO Auto-generated method stub
		String sql = "delete  from staff where staff_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, staffId);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int deleteListStaff(ArrayList<Staff> staffList, Connection con) {
		// TODO Auto-generated method stub
		String sql = "delete  from staff where staff_id = ?";
		int [] rs = null;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			for(int i = 0; i<staffList.size(); i++){
				pstm.setInt(1, staffList.get(i).getStaffId());
				pstm.addBatch();
			}
			rs = pstm.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs[0];
	}

	@Override
	public int updateStaff(Staff staff, Connection con) {
		// TODO Auto-generated method stub
		String sql = "update  staff set staff_id = ?,staff_name = ?,staff_number = ?,staff_department = ?,staff_group = ?,staff_arrangeId = ?,staff_address = ?,staff_lineId = ?,staff_siteId = ? where staff_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			getPreSta(pstm, staff);
			pstm.setInt(10, staff.getStaffId());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public Staff getStaffByStaffId(int staffId, Connection con) {
		// TODO Auto-generated method stub
		Staff staff = null;
		String sql = "select * from staff where staff_id=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, staffId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				staff = getStaffOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return staff;
	}

	@Override
	public Staff getStaffByNumber(String number, Connection con) {
		// TODO Auto-generated method stub
		Staff staff = null;
		String sql = "select * from staff where staff_number=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, number);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				staff = getStaffOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return staff;
	}

	@Override
	public ArrayList<Staff> getStaffByName(String name, Connection con) {
		// TODO Auto-generated method stub
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		String sql = "select * from staff where staff_name=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				staffList.add(getStaffOne(rs)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return staffList;
	}

	@Override
	public ArrayList<Staff> getStaffByDepartment(String department, Connection con) {
		// TODO Auto-generated method stub
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		String sql = "select * from staff where staff_department=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, department);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				staffList.add(getStaffOne(rs)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return staffList;
	}

	@Override
	public ArrayList<Staff> getStaffByGroup(String group, Connection con) {
		// TODO Auto-generated method stub
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		String sql = "select * from staff where staff_group=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, group);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				staffList.add(getStaffOne(rs)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return staffList;
	}

	@Override
	public ArrayList<Staff> getStaffByArrangeId(int arrangeId, Connection con) {
		// TODO Auto-generated method stub
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		String sql = "select * from staff where staff_arrangeId=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, arrangeId);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				staffList.add(getStaffOne(rs)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return staffList;
	}

	@Override
	public ArrayList<Staff> getStaffByLineId(int lineId, Connection con) {
		// TODO Auto-generated method stub
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		String sql = "select * from staff where staff_lineId=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, lineId);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				staffList.add(getStaffOne(rs)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return staffList;
	}

	@Override
	public ArrayList<Staff> getStaffBySiteId(int siteId, Connection con) {
		// TODO Auto-generated method stub
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		String sql = "select * from staff where staff_siteId=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, siteId);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				staffList.add(getStaffOne(rs)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return staffList;
	}

	@Override
	public int getStaffNum(Connection con) {
		String sql = "select count(*) from staff";
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
	public ArrayList<Staff> getStaffByPage(int startPage, int pageNum,
			Connection con) {
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		String sql = "select * from staff  limit ? ,?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, (startPage-1)*pageNum);
			pstm.setInt(2, pageNum);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				staffList.add(getStaffOne(rs)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return staffList;
	}

}
