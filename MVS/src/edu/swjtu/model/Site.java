package edu.swjtu.model;

/**
 * 
 * Site.java类
 * 2016年7月9日
 * @author wujunyu
 * TODo
 */
public class Site {
	private int siteId;
	private int peoNum;
	private String lineId;
	private String order;
	private int delay;
	private double latitude;
	private double longitude;
	private String address;
	private String name;
	private int bufftag;
	public int getBufftag() {
		return bufftag;
	}
	public void setBufftag(int bufftag) {
		this.bufftag = bufftag;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getPeoNum() {
		return peoNum;
	}
	public void setPeoNum(int peoNum) {
		this.peoNum = peoNum;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double d) {
		this.latitude = d;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
