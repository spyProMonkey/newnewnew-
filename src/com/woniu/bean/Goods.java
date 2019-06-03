package com.woniu.bean;

public class Goods {
	private int gid;
	private String gname;
	private String brand;
	private int tid;
	private double price;
	private String unit;
	private String image;
	private boolean saleing;
	private int priority;
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isSaleing() {
		return saleing;
	}
	public void setSaleing(boolean saleing) {
		this.saleing = saleing;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Goods(int gid, String gname, String brand, int tid, double price,
				String unit, String image, boolean saleing, int priority) {
		this.gid = gid;
		this.gname = gname;
		this.brand = brand;
		this.tid = tid;
		this.price = price;
		this.unit = unit;
		this.image = image;
		this.saleing = saleing;
		this.priority = priority;
	}
	public Goods() {
	}
	@Override
	public String toString() {
		return "编号："+gid+"；名称："+gname+"；品牌："+brand+"；类型："+tid+"；价格："+price+"；单位："+unit+"；图片："+image+"；在售："+saleing+"；级别："+priority;
	}
}
