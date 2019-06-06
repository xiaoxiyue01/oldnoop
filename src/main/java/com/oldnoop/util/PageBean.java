package com.oldnoop.util;

import java.util.List;

public class PageBean {

	/**
	 * 当前页码,从1开始数数
	 */
	private int pageNum = 1;
	/**
	 * 每页条数
	 */
	private int pageSize = 10;
	/**
	 * 总条数
	 */
	private int total;
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 分页数据
	 */
	private List<?> data;
	
	/**
	 * 显示的页码开始页
	 */
	private int displayStart = 1;
	/**
	 * 显示的页码个数
	 */
	private int displayNum = 5;
	
	public PageBean(int pageNum, int pageSize, int total) {
		init(pageNum, pageSize, total, 0);
	}
	
	public PageBean(int pageNum, int pageSize, int total, int displayNum) {
		init(pageNum, pageSize, total, displayNum);
	}
	
	private void init(int pageNum, int pageSize, int total, int displayNum) {
		this.total = total;
		if(pageNum >= 0){
			this.pageNum = pageNum;
		}
		if(pageSize > 0){
			this.pageSize = pageSize;
		}
		if(this.total % this.pageSize ==0){
			this.totalPage = this.total / this.pageSize ;
		}else{
			this.totalPage = (int)(this.total / this.pageSize)  + 1;
		}
		if(this.pageNum > this.totalPage && this.totalPage > 0){
			this.pageNum = this.totalPage;
		}
		if(displayNum > 0){
			this.displayNum = displayNum;
		}
		
		int start = this.pageNum -  this.displayNum / 2;
		this.displayStart = start >=1 ? start : 1;		
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public int getDisplayStart() {
		return displayStart;
	}

	public void setDisplayStart(int displayStart) {
		this.displayStart = displayStart;
	}

	public int getDisplayNum() {
		return displayNum;
	}

	public void setDisplayNum(int displayNum) {
		this.displayNum = displayNum;
	}

	@Override
	public String toString() {
		return "PageBean [pageNum=" + pageNum + ", pageSize=" + pageSize + ", total=" + total + ", totalPage="
				+ totalPage + ", data=" + data + ", displayStart=" + displayStart + ", displayNum=" + displayNum + "]";
	}
}
