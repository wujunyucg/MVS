package edu.swjtu.model;
 /**
  * 
  * Line.java类
  * 2016年6月17日
  * @author wujunyu
  * TODo
  */
public class Line {
	private int lineId;
	private int num;
	private String siteId;
	private String carId;
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
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
