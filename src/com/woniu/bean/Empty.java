package com.woniu.bean;

import java.util.ArrayList;
import java.util.List;

public class Empty {
	private String gid = "无";
	private String gname = "无";
	private String brand = "无";
	private String price = "无";
	private String unit = "无";
	private String image = "无";
	private String saleing = "无";
	private String priority = "无";
	private String tid = "无";
	private String tname = "无";
	private String sid = "无";
	private String amount = "无";
	private String inprice = "无";
	private String sdate = "无";
	private String supplier = "无";
	private String purchaser = "无";
	public String getGid() {
		return gid;
	}
	public String getGname() {
		return gname;
	}
	public String getBrand() {
		return brand;
	}
	public String getPrice() {
		return price;
	}
	public String getUnit() {
		return unit;
	}
	public String getImage() {
		return image;
	}
	public String getSaleing() {
		return saleing;
	}
	public String getPriority() {
		return priority;
	}
	public String getTid() {
		return tid;
	}
	public String getTname() {
		return tname;
	}
	public String getSid() {
		return sid;
	}
	public String getAmount() {
		return amount;
	}
	public String getInprice() {
		return inprice;
	}
	public String getSdate() {
		return sdate;
	}
	public String getSupplier() {
		return supplier;
	}
	public String getPurchaser() {
		return purchaser;
	}
	public static List<Empty> getEmpty(){
		List<Empty> empty = new ArrayList<Empty>();
		empty.add(new Empty());
		return empty;
	}
}
