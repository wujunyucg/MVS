package edu.swjtu.model;
 /**
  * 
  * Line.java类
  * 2016年7月9日
  * @author wujunyu
  * TODo
  */
public class Line {
	private int lineId;
	private int num;
	private int siteId;
	private int carId;
	private String name;
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
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
}
