package edu.swjtu.model;

public class DayRecord {
	private int dayId;
	private String day_staffNumber;
	private int day_siteId;
	private int day_lineId;
	private String day_date;
	public int getDayId() {
		return dayId;
	}
	public void setDayId(int dayId) {
		this.dayId = dayId;
	}
	public int getDay_siteId() {
		return day_siteId;
	}
	public void setDay_siteId(int day_siteId) {
		this.day_siteId = day_siteId;
	}
	public String getDay_date() {
		return day_date;
	}
	public void setDay_date(String day_date) {
		this.day_date = day_date;
	}
	public String getDay_staffNumber() {
		return day_staffNumber;
	}
	public void setDay_staffNumber(String day_staffNumber) {
		this.day_staffNumber = day_staffNumber;
	}
	public int getDay_lineId() {
		return day_lineId;
	}
	public void setDay_lineId(int day_lineId) {
		this.day_lineId = day_lineId;
	}
	
	//复写为了加入hashset去重
	@Override
	public int hashCode() {
		return this.day_staffNumber.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof DayRecord)){
			return false;
		}
		DayRecord d = (DayRecord)obj;
		return d.day_staffNumber.equals(this.day_staffNumber);
	}
}
