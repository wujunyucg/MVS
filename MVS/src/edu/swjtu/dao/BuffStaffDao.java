package edu.swjtu.dao;

import java.sql.Connection;
import java.util.ArrayList;

import edu.swjtu.model.BuffStaff;


/**
 * 
 * BuffStaffDao.java类
 * 2016年7月16日
 * @author wujunyu
 * TODo
 */
public interface BuffStaffDao {
	/**
	 * 
	 * 2016年7月16日下午4:12:50
	 * @author wujunyu
	 * TODO
	 * @param bstaff
	 * @param con
	 * @return
	 */
	public int addOneBuffStaff(BuffStaff bstaff,Connection con);
	/**
	 * 
	 * 2016年7月16日下午4:13:26
	 * @author wujunyu
	 * TODO
	 * @param bstaffList
	 * @param con
	 * @return
	 */
	public int addListBuffStaff(ArrayList<BuffStaff> bstaffList,Connection con);
}
