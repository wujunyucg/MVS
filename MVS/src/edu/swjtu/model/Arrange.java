package edu.swjtu.model;

import javax.xml.crypto.Data;

/**
 * 
 * Arrange.java类
 * 2016年7月9日
 * @author wujunyu
 * TODo
 */
public class Arrange {
	private int arrangeId;
	private int lineId;
	private int carId;
	private String name;
	private Data time;
	public int getArrangeId() {
		return arrangeId;
	}
	public void setArrangeId(int arrangeId) {
		this.arrangeId = arrangeId;
	}
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Data getTime() {
		return time;
	}
	public void setTime(Data time) {
		this.time = time;
	}
}
