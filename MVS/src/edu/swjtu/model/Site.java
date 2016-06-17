package edu.swjtu.model;

/**
 * 
 * Site.java类
 * 2016年6月17日
 * @author wujunyu
 * TODo
 */
public class Site {
	private int siteId;
	private int peoNum;
	private int lineId;
	private int order;
	private int delay;
	private String latitude;
	private String longitude;
	private String address;
	private String name;
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
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
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
