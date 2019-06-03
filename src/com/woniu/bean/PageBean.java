package com.woniu.bean;

public class PageBean {
	private int page;
	private int pageRow = 5;
	private int countPage;
	private int countRow;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageRow() {
		return pageRow;
	}
	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}
	public int getCountPage() {
		return countPage;
	}
	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}
	public int getCountRow() {
		return countRow;
	}
	public void setCountRow(int countRow) {
		this.countRow = countRow;
	}
	public PageBean(int page, int pageRow, int countPage, int countRow) {
		this.page = page;
		this.pageRow = pageRow;
		this.countPage = countPage;
		this.countRow = countRow;
	}
	public PageBean(int page, int countRow) {
		this.page = page;
		this.countRow = countRow;
		this.countPage = (this.countRow%pageRow==0&&this.countRow!=0)?(this.countRow/pageRow):(this.countRow/pageRow+1);
	}
	public PageBean() {
	}
}
