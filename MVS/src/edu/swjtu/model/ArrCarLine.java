package edu.swjtu.model;

public class ArrCarLine {
	private int arrangeId;
	private String arrName;
	private String date;
	private String time;
	private String lineName;
	private String licensePlate;
	private String driver;
	
	public ArrCarLine(){
		
	}
	public ArrCarLine(Arrange arr,Car car,Line line){
		this.arrangeId = arr.getArrangeId();
		this.arrName = arr.getName();
		this.date = arr.getDate();
		this.time = arr.getTime();
		this.licensePlate = car.getLicensePlate();
		this.lineName = line.getName();
		this.driver = car.getDriver();
	}
	public int getArrangeId() {
		return arrangeId;
	}
	public void setArrangeId(int arrangeId) {
		this.arrangeId = arrangeId;
	}
	public String getArrName() {
		return arrName;
	}
	public void setArrName(String arrName) {
		this.arrName = arrName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
}
