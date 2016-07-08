package edu.swjtu.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import edu.swjtu.model.Staff;

/**
 * 
 * StaffDao.java类
 * 2016年7月8日
 * @author wujunyu
 * TODo
 */
public interface StaffDao {

	/**
	 * 
	 * 2016年7月8日下午4:21:55
	 * @author wujunyu
	 * TODO 添加一个员工
	 * @param staff
	 * @param con
	 * @return
	 */
	public int addOneStaff(Staff staff,Connection con);
	/**
	 * 
	 * 2016年7月8日下午4:22:58
	 * @author wujunyu
	 * TODO 添加一群员工
	 * @param staffList
	 * @param con
	 * @return
	 */
	public int addListStaff(ArrayList<Staff> staffList,Connection con);
	/**
	 * 
	 * 2016年7月8日下午4:24:15
	 * @author wujunyu
	 * TODO 删除一个员工
	 * @param staffId
	 * @param con
	 * @return
	 */
	public int deleteOneStaff(int staffId,Connection con);
	/**
	 * 
	 * 2016年7月8日下午4:26:20
	 * @author wujunyu
	 * TODO 删除一群员工
	 * @param staffList
	 * @param con
	 * @return
	 */
	public int deleteListStaff(ArrayList<Staff> staffList, Connection con);
	/**
	 * 
	 * 2016年7月8日下午4:28:16
	 * @author wujunyu
	 * TODO 更改员工信息
	 * @param staff
	 * @param con
	 * @return
	 */
	public int updateStaff(Staff staff, Connection con);
	
	/**
	 * 
	 * 2016年7月8日下午4:28:37
	 * @author wujunyu
	 * TODO 根据id查询员工
	 * @param staffId
	 * @param con
	 * @return
	 */
	public Staff getStaffByStaffId(int staffId,Connection con);
	/**
	 * 
	 * 2016年7月8日下午4:37:19
	 * @author wujunyu
	 * TODO 根据工号查询员工
	 * @param staffId
	 * @param con
	 * @return
	 */
	public Staff getStaffByNumber(String number,Connection con);
	/**
	 * 
	 * 2016年7月8日下午4:38:01
	 * @author wujunyu
	 * TODO 根据员工姓名查询员工
	 * @param name
	 * @param con
	 * @return
	 */
	public ArrayList getStaffByName(String name,Connection con);
	/**
	 * 
	 * 2016年7月8日下午4:38:47
	 * @author wujunyu
	 * TODO 根据部门查询员工
	 * @param department
	 * @param con
	 * @return
	 */
	public ArrayList  getStaffByDepartment(String department,Connection con);
	/**
	 * 
	 * 2016年7月8日下午4:39:07
	 * @author wujunyu
	 * TODO 根据组别查询员工
	 * @param group
	 * @param con
	 * @return
	 */
	public ArrayList getStaffByGroup(String group,Connection con);
	/**
	 * 
	 * 2016年7月8日下午4:39:23
	 * @author wujunyu
	 * TODO 根据班次id查询员工
	 * @param arrangeId
	 * @param con
	 * @return
	 */
	public ArrayList  getStaffByArrangeId(int arrangeId,Connection con);
	/**
	 * 
	 * 2016年7月8日下午4:39:56
	 * @author wujunyu
	 * TODO 根据线路id查询员工
	 * @param lineId
	 * @param con
	 * @return
	 */
	public ArrayList getStaffByLineId(int lineId,Connection con);
	/**
	 * 
	 * 2016年7月8日下午4:40:15
	 * @author wujunyu
	 * TODO 根据站点id查询员工
	 * @param siteId
	 * @param con
	 * @return
	 */
	public ArrayList getStaffBySiteId(int siteId,Connection con);
}
