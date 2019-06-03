package com.woniu.bean;

public class Gtype {
	private int tid;
	private String tname;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public Gtype() {
	}
	public Gtype(int tid, String tname) {
		this.tid = tid;
		this.tname = tname;
	}
	@Override
	public String toString() {
		return "ID："+tid+",名："+tname;
	}
}
