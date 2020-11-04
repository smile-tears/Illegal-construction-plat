package com.plat.caseinfo.service;

import com.plat.common.entity.Page;

import javax.servlet.http.HttpServletRequest;

import com.plat.caseinfo.entity.CaseInfoCity;

public interface CaseInfoCityService {
	public Object save(CaseInfoCity caseInfoCity);

	public Object deleteByIds(String[] ids);

	public Object Update(CaseInfoCity caseInfoCity);

	public Object find(CaseInfoCity caseInfoCity, Page page);
	
	public Object find2(String source,CaseInfoCity caseInfoCity, Page page,HttpServletRequest request );
}