package edu.swjtu.model;

public class Lost {
	private String name;
	private String content;
	private String date;
	private int lostId;
	private String contact;
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getLostId() {
		return lostId;
	}
	public void setLostId(int lostId) {
		this.lostId = lostId;
	}
	

}
