package com.plat.caseinfo.service;

import com.plat.common.entity.Page;

import javax.servlet.http.HttpServletRequest;

import com.plat.caseinfo.entity.CaseInfoRequestLogArea;

public interface CaseInfoRequestLogAreaService {
	public Object save(CaseInfoRequestLogArea caseInfoRequestLogArea,HttpServletRequest request);

	public Object deleteByIds(String[] ids);

	public Object Update(CaseInfoRequestLogArea caseInfoRequestLogArea);

	public Object find(CaseInfoRequestLogArea caseInfoRequestLogArea, Page page);
}