package com.tedu.entity;

public class Paging {

	private long totalRows;
	private int totalPages;
	private int currentPage;
	private int recordPerPage = 10;
	private int offset;
	
	public Paging(int recordPerPage) {
		super();
		this.recordPerPage = recordPerPage;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		if(totalRows > 0) {
			totalPages =(int) Math.ceil(totalRows / (double)recordPerPage);
		}
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	public int getOffset() {
		if(currentPage > 0) {
			offset = recordPerPage*(currentPage - 1) ;
		}
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
}
