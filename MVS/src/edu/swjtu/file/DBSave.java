package edu.swjtu.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Properties;

import org.junit.Test;

/**
 * 数据库备份与恢复 2016年7月15日下午8:16:09
 * 
 * @author jimolonely TODO
 */
public class DBSave {
	/**
	 * 执行dos命令
	 * 
	 * @param cmd
	 * @return
	 * @throws InterruptedException
	 */
	public String execCmd(String cmd) throws InterruptedException {
		StringBuffer sb = new StringBuffer("");
		StringBuffer str = new StringBuffer();
		Properties pro = getPprVue("data_save.properties");
		String path = pro.getProperty("mysqlpath");
		System.out.println("path=" + path);
		str.append("cmd.exe /c " + path).append(cmd);
		System.out.println(str); // 打印执行的命令
		Process ls_proc;
		try {
			ls_proc = Runtime.getRuntime().exec(str.toString());
			String line = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(
					ls_proc.getErrorStream(), Charset.forName("GBK")));
			// 读取ErrorStream很关键，这个解决了挂起的问题。

			while ((line = br.readLine()) != null) {
				System.out.println(line + "7");
			}
			System.out.println("*");
			// process.waitFor();
			if (ls_proc.waitFor() != 0) {
				System.out.println("no");
				return null;// 失败
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(
					ls_proc.getInputStream()));
			String ss = "";
			while ((ss = in.readLine()) != null) {
				sb.append(ss).append("\n");
			}
			System.out.println(sb + "**");
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * 执行mysql数据库备份
	 * 
	 * @param ip
	 * @param username
	 * @param password
	 * @param datebaseName
	 * @param filePath
	 * @return
	 * @throws InterruptedException
	 */
	public boolean backupDatebase(String filePath) throws InterruptedException {
		Properties pro = getPprVue("data_save.properties");
		String ip = pro.getProperty("ip");
		String user = pro.getProperty("user");
		String pass = pro.getProperty("password");
		String dbName = pro.getProperty("dbName");
		String strCommand = "mysqldump -h " + ip + " -u" + user + " -p" + pass
				+ " " + dbName + " > " + filePath;
		String result = execCmd(strCommand);
		System.out.println(result);
		return true;
	}

	public boolean loadDataBase(String sqlpath) throws InterruptedException {
		Properties pro = getPprVue("data_save.properties");
		String ip = pro.getProperty("ip");
		String user = pro.getProperty("user");
		String pass = pro.getProperty("password");
		String path = pro.getProperty("mysqlpath");
		String dbName = pro.getProperty("dbName");
		StringBuffer cmd = new StringBuffer("cmd.exe /c ");
		cmd.append(path).append(
				"mysql -h" + ip + " -u" + user + " -p" + pass + " " + dbName
						+ " < " + sqlpath);
		System.out.println("导入cmd：" + cmd);
		try {
			Process ls_proc = Runtime.getRuntime().exec(cmd.toString());
			String line = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(
					ls_proc.getErrorStream(), Charset.forName("GBK")));
			// 读取ErrorStream很关键，这个解决了挂起的问题。

			while ((line = br.readLine()) != null) {
				System.out.println(line + "error");
			}
			System.out.println("*");
			if (ls_proc.waitFor() != 0) {
				System.out.println("no");
				return false;// 失败
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 读取属性文件
	 */
	public Properties getPprVue(String properName) {
		InputStream inputStream = DBSave.class.getClassLoader()
				.getResourceAsStream(properName);
		Properties p = new Properties();
		try {
			p.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

	public String getProValue(String key) throws IOException {
		// 获取绝对路径
		String filePath = DBSave.class.getResource("/data_save.properties")
				.getPath();
//		String filePath = new File("").getCanonicalPath()+"/src/data_save.properties";
		System.out.println(filePath);
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					filePath));
			props.load(in);
			in.close();
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setProValue(String key, String value) {
		// 获取绝对路径
		// String filePath =
		// DBSave.class.getResource("/src/data_save.properties").getPath();
//		String filePath = DBSave.class.getClassLoader()
//				.getResource("data_save.properties").getPath();
		String filePath = "";
		try {
			filePath = new File("").getCanonicalPath()+"/src/data_save.properties";
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(filePath);
		Properties prop = new Properties();
		try {
			File file = new File(filePath);
			if (!file.exists()){
				file.createNewFile();
			}
			InputStream fis = new FileInputStream(file);
			prop.load(fis);
			// 一定要在修改值之前关闭fis
			fis.close();
			OutputStream fos = new FileOutputStream(filePath);
			prop.setProperty(key, value);
			// 保存，并加入注释
			prop.store(fos, "Update '" + key + "' value");
			fos.close();
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating " + value
					+ " value error");
		}
	}
}
