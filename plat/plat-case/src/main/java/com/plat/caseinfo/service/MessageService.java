package com.plat.caseinfo.service;

import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.Message;

public interface MessageService {
	public Object save(Message message);

	public Object deleteByIds(String[] ids);

	
 
	public Object find(Message message, Page page);
 
}