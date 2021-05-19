package com.plat.caseinfo.service;

import com.plat.common.entity.Page;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.plat.caseinfo.entity.CaseInfoCity;

public interface CaseInfoCityService {
	public Object save(CaseInfoCity caseInfoCity);

	public Object deleteByIds(String[] ids);

	public Object Update(CaseInfoCity caseInfoCity);

	public Object find(CaseInfoCity caseInfoCity, Page page);
	
	public JSONObject find2(CaseInfoCity caseInfoCity, Page page,HttpServletRequest request,String source );
	
	public Object report();
	
	public Object report2(String startDate,String endDate,String grid,String manager);
	
	public Object exportWord(String id);
}