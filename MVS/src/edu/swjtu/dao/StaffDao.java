package edu.swjtu.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import edu.swjtu.model.Staff;

/**
 * 
 * StaffDao.java类
 * 2016年7月9日
 * @author wujunyu
 * TODo
 */
public interface StaffDao {

	/**
	 * 
	 * 2016年7月9日上午9:52:27
	 * @author wujunyu
	 * TODO 添加一个员工
	 * @param staff
	 * @param con
	 * @return
	 */
	public int addOneStaff(Staff staff,Connection con);
	/**
	 * 
	 * 2016年7月9日上午9:52:44
	 * @author wujunyu
	 * TODO 添加一个列表的员工
	 * @param staffList
	 * @param con
	 * @return
	 */
	public int addListStaff(ArrayList<Staff> staffList,Connection con);
	/**
	 * 
	 * 2016年7月9日上午9:53:04
	 * @author wujunyu
	 * TODO 删除一个员工
	 * @param staffId
	 * @param con
	 * @return
	 */
	public int deleteOneStaff(int staffId,Connection con);
	/**
	 * 
	 * 2016年7月9日上午9:53:21
	 * @author wujunyu
	 * TODO 删除一个列表的员工
	 * @param staffList
	 * @param con
	 * @return
	 */
	public int deleteListStaff(ArrayList<Staff> staffList, Connection con);
	/**
	 * 
	 * 2016年7月9日上午9:53:38
	 * @author wujunyu
	 * TODO 更新一个员工
	 * @param staff
	 * @param con
	 * @return
	 */
	public int updateStaff(Staff staff, Connection con);
	
	/**
	 * 
	 * 2016年7月9日上午9:53:57
	 * @author wujunyu
	 * TODO 根据员工id查询一个员工
	 * @param staffId
	 * @param con
	 * @return
	 */
	public Staff getStaffByStaffId(int staffId,Connection con);
	/**
	 * 
	 * 2016年7月9日上午9:54:21
	 * @author wujunyu
	 * TODO 根据工号得到一个员工
	 * @param number
	 * @param con
	 * @return
	 */
	public Staff getStaffByNumber(String number,Connection con);
	/**
	 * 
	 * 2016年7月9日上午9:54:44
	 * @author wujunyu
	 * TODO 根据员工姓名得到员工列表
	 * @param name
	 * @param con
	 * @return
	 */
	public ArrayList<Staff> getStaffByName(String name,Connection con);
	/**
	 * 
	 * 2016年7月9日上午9:55:09
	 * @author wujunyu
	 * TODO 根据部门得到员工列表
	 * @param department
	 * @param con
	 * @return
	 */
	public ArrayList<Staff>  getStaffByDepartment(String department,Connection con);
	/**
	 * 
	 * 2016年7月9日上午9:55:30
	 * @author wujunyu
	 * TODO 根据组别得到员工列表
	 * @param group
	 * @param con
	 * @return
	 */
	public ArrayList<Staff> getStaffByGroup(String group,Connection con);
	/**
	 * 
	 * 2016年7月9日上午9:55:48
	 * @author wujunyu
	 * TODO 根据班次id得到员工列表
	 * @param arrangeId
	 * @param con
	 * @return
	 */
	public ArrayList <Staff> getStaffByArrangeId(int arrangeId,Connection con);
	/**
	 * 
	 * 2016年7月9日上午9:56:08
	 * @author wujunyu
	 * TODO 根据线路id得到员工列表
	 * @param lineId
	 * @param con
	 * @return
	 */
	public ArrayList<Staff> getStaffByLineId(int lineId,Connection con);
	/**
	 * 
	 * 2016年7月9日上午9:56:37
	 * @author wujunyu
	 * TODO 根据站点id得到员工列表
	 * @param siteId
	 * @param con
	 * @return
	 */
	public ArrayList<Staff> getStaffBySiteId(int siteId,Connection con);
}
