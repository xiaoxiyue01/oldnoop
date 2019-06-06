package com.oldnoop.util;

import java.util.List;

public class EasyuiDatagrid {

	private int pageNum;
	private int pageSize;
	private int total;
	private List<?> rows;
	
	public int getPageNum() {
		return pageNum;
	}

	public EasyuiDatagrid setPageNum(int pageNum) {
		this.pageNum = pageNum;
		return this;
	}

	public int getPageSize() {
		return pageSize;
	}

	public EasyuiDatagrid setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public int getTotal() {
		return total;
	}

	public EasyuiDatagrid setTotal(int total) {
		this.total = total;
		return this;
	}

	public List<?> getRows() {
		return rows;
	}

	public EasyuiDatagrid setRows(List<?> rows) {
		this.rows = rows;
		return this;
	}
	
	public EasyuiDatagrid setPageBean(PageBean pb) {
		this.setPageNum(pb.getPageNum());
		this.setPageSize(pb.getPageSize());
		this.setRows(pb.getData());
		this.setTotal(pb.getTotal());
		return this;
	}

	@Override
	public String toString() {
		return "JEasyuiDatagrid [pageNo=" + pageNum + ", pageSize=" + pageSize + ", total=" + total + ", rows=" + rows
				+ "]";
	}
	
}
