package com.plat.common.entity;

import lombok.Data;

@Data
public class Page {

	private Integer pageNo;
	private Integer pageSize;
	private long totalCount;
	private long totalPage;
	
	public Page() {
		
	}
	public Page(Integer pageNo,Integer pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
}
