package com.plat.caseinfo.service;

import com.plat.common.entity.Page;

import javax.servlet.http.HttpServletRequest;

import com.plat.caseinfo.entity.CaseInfoRequestLogStreet;

public interface CaseInfoRequestLogStreetService {
	public Object save(CaseInfoRequestLogStreet caseInfoRequestLogStreet,HttpServletRequest request);

	public Object deleteByIds(String[] ids);

	public Object Update(CaseInfoRequestLogStreet caseInfoRequestLogStreet);

	public Object find(CaseInfoRequestLogStreet caseInfoRequestLogStreet, Page page);
}