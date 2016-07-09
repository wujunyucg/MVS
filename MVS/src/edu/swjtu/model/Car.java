package edu.swjtu.model;

import javax.xml.crypto.Data;

/**
 * 
 * Car.java类
 * 2016年7月9日
 * @author wujunyu
 * TODo
 */
public class Car {
	private int carId;
	private int number;
	private String licensePlate;
	private String brand;
	private String drivingLicense;
	private String license;
	private String arrangeId;
	private String driver;
	private Data registrationDate;
	private Data insuranceDate;
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDrivingLicense() {
		return drivingLicense;
	}
	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getArrangeId() {
		return arrangeId;
	}
	public void setArrangeId(String arrangeId) {
		this.arrangeId = arrangeId;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public Data getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Data registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Data getInsuranceDate() {
		return insuranceDate;
	}
	public void setInsuranceDate(Data insuranceDate) {
		this.insuranceDate = insuranceDate;
	}
}
