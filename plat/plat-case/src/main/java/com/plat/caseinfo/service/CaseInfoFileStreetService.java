package com.plat.caseinfo.service;

import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.CaseInfoFileStreet;

public interface CaseInfoFileStreetService {
	public Object save(CaseInfoFileStreet caseInfoFileStreet);

	public Object deleteByIds(String[] ids);

	public Object Update(CaseInfoFileStreet caseInfoFileStreet);

	public Object find(CaseInfoFileStreet caseInfoFileStreet, Page page);
}