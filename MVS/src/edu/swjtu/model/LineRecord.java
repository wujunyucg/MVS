package edu.swjtu.model;

public class LineRecord {
	private int linerecordId;
	private int lineId;
	private String date;
	private int num;
	private double rate;
	private String staffIds;
	public int getLinerecordId() {
		return linerecordId;
	}
	public void setLinerecordId(int linerecordId) {
		this.linerecordId = linerecordId;
	}
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
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
