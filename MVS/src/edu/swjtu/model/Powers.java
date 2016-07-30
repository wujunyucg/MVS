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
			"人员数据维护","人员数据同步","人员信息查询",
			"安排线路","查询线路",
			"班次查询","班次数据维护",
			"站点数据维护",
			"新车入厂","查询车辆信息","车辆数据维护"};
	final static String[]power_n={
		"A","D","C","F",
		"A","D","C","F",
		"A","D","C","F","E",
		"A","D","C","F","E",
		"A","D","C","F","E"};
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
			if(powers[i].contains("人员")){
				set.add(0);
			}else if(powers[i].contains("线路")){
				set.add(1);
			}else if(powers[i].contains("班次")){
				set.add(2);
			}else if(powers[i].contains("站点")){
				set.add(3);
			}else if(powers[i].contains("车")){
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
		Set<Integer> set = new HashSet<Integer>();//集合为了保证数据不重复 
		for(int i=0;i<powers.length;i++){
			int id = Integer.parseInt(powers[i]);
			if(id<8){
				set.add(id/4);
			}else{
				set.add((id+2)/5);
			}
		}
		String p = "";
		for(int i : set){
			p+=powerType[i] + "(";
			if(i<2){
				for(int j=0;j<powers.length;j++){
					if(Integer.parseInt(powers[j]) >= i*4 && Integer.parseInt(powers[j]) <= i*4+3)
						p += power_n[Integer.parseInt(powers[j])] + " ";
					else if(Integer.parseInt(powers[j]) > i*4+3)
						break;
				}
			}
			else if(i==2){
				for(int j=0;j<powers.length;j++){
					if(Integer.parseInt(powers[j]) >= 8 && Integer.parseInt(powers[j]) <= 12)
						p += power_n[Integer.parseInt(powers[j])] + " ";
					else if(Integer.parseInt(powers[j]) > 12)
						break;
				}
			}
			else{
				for(int j=0;j<powers.length;j++){
					if(Integer.parseInt(powers[j]) >= i*5-2 && Integer.parseInt(powers[j]) <= i*5+2)
						p += power_n[Integer.parseInt(powers[j])] + " ";
					else if(Integer.parseInt(powers[j]) > i*5+2)
						break;
				}
			}
			p = p.substring(0,p.length()-1); 
			p += ") , ";
		}
		p = p.substring(0,p.length()-1); 
		return p;
	}
}
