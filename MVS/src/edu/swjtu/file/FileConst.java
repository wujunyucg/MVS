package edu.swjtu.file;

import java.io.IOException;
import java.util.Properties;

public class FileConst {
	private static Properties properties= new Properties();
	static{
		try {
			properties.load(ReadUploadFileType.class.getClassLoader().getResourceAsStream("uploadConst.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		String value = (String)properties.get(key);
		return value.trim();
	}
}
