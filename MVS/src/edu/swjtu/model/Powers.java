package edu.swjtu.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 用于存储权限编号与对应的内容
 * 2016年7月10日下午5:26:15
 * @author jimolonely
 * TODO
 */
public class Powers {
	
	final static String[]power={
			"人员维护","人员同步","人员查询",
			"线路维护","线路查询",
			"班次查询","班次维护",
			"站点维护",
			"新车入厂","车辆查询","车辆维护"};
	final static String[]powerType = {
		"人员管理","线路管理","排班管理","站点管理","车辆管理"	
	};
	/**
	 * 通过ids返回所有权限
	 * 2016年7月10日下午6:09:25
	 * @author jimolonely
	 * @return
	 */
	public static ArrayList<String> getPower(String ids){
		ArrayList<String>list = new ArrayList<String>();
		String[]ss = ids.split(",");
		for(String s : ss){
			int i = Integer.parseInt(s);
			list.add(power[i]);
		}
		return list;
	}
	/**
	 * 通过传过来的id返回权限大类
	 * 2016年7月10日下午6:08:35
	 * @author jimolonely
	 * @param ids
	 * @return
	 */
	public static String getPowerType(String ids){
		String[]powers = ids.split(",");
		Set<Integer> set = new HashSet<Integer>();//集合为了保证数据不重复 
		for(int i=0;i<powers.length;i++){
			if(powers[i].equals("0")||powers[i].equals("1")||powers[i].equals("2")){
				set.add(0);
			}else if(powers[i].equals("3")||powers[i].equals("4")){
				set.add(1);
			}else if(powers[i].equals("5")||powers[i].equals("6")){
				set.add(2);
			}else if(powers[i].equals("7")){
				set.add(3);
			}else if(powers[i].equals("8")||powers[i].equals("9")||powers[i].equals("10")){
				set.add(4);
			}
		}
		String p = "";
		for(int i : set){
			p+=powerType[i]+" ";
		}
		return p;
	}
	
	public static String getPowerType_CU(String ids){
		String[]powers = ids.split(",");
		String p = "";
		for(int i=0;i<powers.length;i++){
			p+=power[Integer.parseInt(powers[i])]+" ";
		}
		return p;
	}
}
