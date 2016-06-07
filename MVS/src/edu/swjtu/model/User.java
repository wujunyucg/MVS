package edu.swjtu.model;
/**
 * User类
 * 2016年6月7日下午5:54:15
 * @author jimolonely
 * TODO
 */
public class User {
	private int userId;
	private String number;
	private String password;
	int adminId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}	
}
