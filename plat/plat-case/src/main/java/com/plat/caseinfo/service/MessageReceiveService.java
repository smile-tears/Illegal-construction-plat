package com.plat.caseinfo.service;

import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.MessageReceive;

public interface MessageReceiveService {
	public Object save(MessageReceive messageReceive);

	public Object deleteByIds(String[] ids);

	public Object read(String messageId,String userid);
	
	public Object find(MessageReceive messageReceive, Page page);
	

}