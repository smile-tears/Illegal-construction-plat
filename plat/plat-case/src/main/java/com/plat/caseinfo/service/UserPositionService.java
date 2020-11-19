package com.plat.caseinfo.service;

import com.plat.common.entity.Page;

import java.util.List;
import java.util.Map;

import com.plat.caseinfo.entity.UserPosition;

public interface UserPositionService {
	public Object save(UserPosition userPosition);

	public Object deleteByIds(String[] ids);

	public Object Update(UserPosition userPosition);

	public Object find(String userid,String startTime,String endTime, Page page);
	
	public List<Map<String, String>> getRecentPosition ();
}