package edu.swjtu.model;

import java.sql.Connection;
import java.sql.SQLException;

import edu.swjtu.impl.ArrangeDaoImpl;

/**
 * 根据排班字符串获取排班名称
 * 2016年7月23日下午4:57:37
 * @author mischief
 * TODO
 */
public class Arranges {

	/**
	 * 根据排班id获取排班名称
	 * 2016年7月23日下午4:58:55
	 * @author mischief7
	 * @param ids
	 * @return
	 * @throws SQLException 
	 */
	public  String getOneArrange(int arr_id, Connection con) throws SQLException{
		String name = new ArrangeDaoImpl().getArrNameById(arr_id, con);
		return name;
	}
	
	public String getAllArrangeName(String arr_ids, Connection con) throws SQLException{
		
		String names = "";
		if(arr_ids.equals("-1")){
			return arr_ids;
		}else{
			String[]ss = arr_ids.split(",");
			for(int i=0;i<ss.length;i++){
				int arr_id = Integer.valueOf(ss[i]).intValue();
				String temp = getOneArrange(arr_id, con);
				if(!temp.equals("-1")){
					names += getOneArrange(arr_id, con) + ",";
				}
			}
			if(names.equals("")||names==""||names==null){
				return "未查询到相应排班名称";
			}else{
				names = names.substring(0,names.length()-1);
				return names;
			}
		}
	}
	
	
}
