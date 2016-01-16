package com.paincupid.springmvc.util;

public class Page {
	private static final long serialVersionUID = 1L;
	
	public int totalCounts=0;	//设置分页的总条目数
	public int pageSize = 20;	//设置每一页的条目数, 默认20条数据
	public int currentPage = 1;	//设置当前的页码
	
	public int getTotalCounts() {
		return totalCounts;
	}
	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	
}
