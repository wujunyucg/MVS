package edu.swjtu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 日期工具类
	 * 2016年7月16日下午3:46:37
	 * @author jimolonely
	 * @return
	 */
	public static String getDateTime(){
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return date;
	}
}
