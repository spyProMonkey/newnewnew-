package com.woniu.bean;

import java.util.Date;

public class Stock {
	private int sid;
	private int gid;
	private int amount;
	private double inprice;
	private Date sdate;
	private String supplier;
	private String purchaser;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getInprice() {
		return inprice;
	}
	public void setInprice(double inprice) {
		this.inprice = inprice;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getPurchaser() {
		return purchaser;
	}
	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}
	public Stock(int sid, int gid, int amount, double inprice,
				 Date sdate, String supplier, String purchaser) {
		this.sid = sid;
		this.gid = gid;
		this.amount = amount;
		this.inprice = inprice;
		this.sdate = sdate;
		this.supplier = supplier;
		this.purchaser = purchaser;
	}
	public Stock() {}
}
