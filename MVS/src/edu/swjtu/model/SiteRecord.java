package edu.swjtu.model;

public class SiteRecord {
	private int siterecordId;
	private int siteId;
	private String date;
	private int num;
	private double rate;
	private String staffIds;
	public int getSiterecordId() {
		return siterecordId;
	}
	public void setSiterecordId(int siterecordId) {
		this.siterecordId = siterecordId;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getStaffIds() {
		return staffIds;
	}
	public void setStaffIds(String staffIds) {
		this.staffIds = staffIds;
	}
	
}
