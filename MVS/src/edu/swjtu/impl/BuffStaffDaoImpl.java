package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.BuffStaffDao;
import edu.swjtu.model.BuffStaff;
import edu.swjtu.model.Staff;

public class BuffStaffDaoImpl implements BuffStaffDao {

	private BuffStaff getStaffOne(ResultSet rs) throws SQLException{
		BuffStaff bstaff = new BuffStaff();
		bstaff.setAddress(rs.getString("staff_address"));
		bstaff.setDepartment(rs.getString("staff_department"));
		bstaff.setGroup(rs.getString("staff_group"));
		bstaff.setName(rs.getString("staff_name"));
		bstaff.setNumber(rs.getString("staff_number"));
		bstaff.setBuffFlag(rs.getInt("staff_buffflag"));
		return bstaff;
	}
	
	private void getPreSta(PreparedStatement ps, BuffStaff bstaff) throws SQLException{
		
		ps.setInt(1,bstaff.getId());
		ps.setString(2,bstaff.getNumber());
		ps.setString(3,bstaff.getName());
		ps.setString(4,bstaff.getDepartment());
		ps.setString(5,bstaff.getGroup());
		ps.setString(6,bstaff.getAddress());
		ps.setInt(7,0);
	}
	
	@Override
	public int addOneBuffStaff(BuffStaff bstaff, Connection con) {
		
		String sql = "insert into buff_staff (staff_id,staff_number,staff_name,staff_department,staff_group,staff_address,staff_buffflag) values (? , ?,?,?,?,?,?)";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			getPreSta(pstm,bstaff);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int addListBuffStaff(ArrayList<BuffStaff> bstaffList, Connection con) {
		BuffStaff bstaff = new BuffStaff();
		String sql = "insert into buff_staff (staff_id,staff_number,staff_name,staff_department,staff_group,staff_address,staff_buffflag) values (? , ?,?,?,?,?,?)";
		int [] rs = null;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			for(int i = 0; i<bstaffList.size(); i++){
				bstaff = bstaffList.get(i);
				getPreSta(pstm,bstaff);
				pstm.addBatch();
			}
			rs = pstm.executeBatch();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs[0];
	}

}
