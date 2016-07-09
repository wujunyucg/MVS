package edu.swjtu.model;

public class CarRecord {
	private int carrecordId;
	private int carId;
	private String date;
	private int num;
	private double rate;
	private String staffIds;
	public int getCarrecordId() {
		return carrecordId;
	}
	public void setCarrecordId(int carrecordId) {
		this.carrecordId = carrecordId;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
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
