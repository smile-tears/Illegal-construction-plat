package com.plat.caseinfo.service;

import com.plat.common.entity.Page;

import javax.servlet.http.HttpServletRequest;

import com.plat.caseinfo.entity.Message;

public interface MessageService {
	public Object save(Message message);

	public Object deleteByIds(String[] ids);

	
 
	public Object find(Message message, Page page,String startDate,String endDate,HttpServletRequest request);
 
}