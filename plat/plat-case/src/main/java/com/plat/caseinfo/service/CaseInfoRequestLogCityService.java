package com.plat.caseinfo.service;

import com.plat.common.entity.Page;

import javax.servlet.http.HttpServletRequest;

import com.plat.caseinfo.entity.CaseInfoRequestLogCity;

public interface CaseInfoRequestLogCityService {
	public Object save(CaseInfoRequestLogCity caseInfoRequestLogCity,HttpServletRequest request);

	public Object deleteByIds(String[] ids);

	public Object Update(CaseInfoRequestLogCity caseInfoRequestLogCity);

	public Object find(CaseInfoRequestLogCity caseInfoRequestLogCity, Page page);
}