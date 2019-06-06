package com.oldnoop.util;

import java.util.List;

public class JQueryDataTable {

	private int draw;
	private int start = 1;
	private int length = 10;
	private int recordsTotal;
	private int recordsFiltered;
	private List<?> data;

	public int getDraw() {
		return draw;
	}

	public JQueryDataTable setDraw(int draw) {
		this.draw = draw;
		return this;
	}

	public int getStart() {
		return start;
	}

	public JQueryDataTable setStart(int start) {
		this.start = start;
		return this;
	}

	public int getLength() {
		return length;
	}

	public JQueryDataTable setLength(int length) {
		this.length = length;
		return this;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public JQueryDataTable setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
		return this;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public JQueryDataTable setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
		return this;
	}

	/**
	 * pageNum 页码从1开始数数
	 * 
	 * @return
	 */
	public int getPageNum() {
		if (start <= 0) {
			return 1;
		}
		return (int) (start / length) + 1;
	}

	public List<?> getData() {
		return data;
	}

	public JQueryDataTable setData(List<?> data) {
		this.data = data;
		return this;
	}

	public int getPageSize() {
		return length;
	}

	public JQueryDataTable incrementDraw() {
		this.draw = this.draw + 1;
		return this;
	}

	public JQueryDataTable setPageBean(PageBean pb) {
		this.setData(pb.getData());
		this.setRecordsTotal(pb.getTotal());
		this.setRecordsFiltered(pb.getTotal());
		return this;
	}

	@Override
	public String toString() {
		return "JQueryDataTable [draw=" + draw + ", start=" + start + ", length=" + length + ", recordsTotal="
				+ recordsTotal + ", recordsFiltered=" + recordsFiltered + ", data=" + data + "]";
	}

}
