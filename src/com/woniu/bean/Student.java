package com.woniu.bean;

public class Student {
	private int sid;
	private String sname;
	private String ssex;
	private String sclass;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public String getSclass() {
		return sclass;
	}
	public void setSclass(String sclass) {
		this.sclass = sclass;
	}
	public Student(int sid, String sname, String ssex, String sclass) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.ssex = ssex;
		this.sclass = sclass;
	}
	public Student() {
		super();
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", ssex=" + ssex + ", sclass=" + sclass + "]";
	}
	
	
}
