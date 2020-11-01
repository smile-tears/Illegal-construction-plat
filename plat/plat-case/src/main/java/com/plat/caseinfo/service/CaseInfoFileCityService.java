package com.plat.caseinfo.service;

import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.CaseInfoFileCity;

public interface CaseInfoFileCityService {
	public Object save(CaseInfoFileCity caseInfoFileCity);

	public Object deleteByIds(String[] ids);

	public Object Update(CaseInfoFileCity caseInfoFileCity);

	public Object find(CaseInfoFileCity caseInfoFileCity, Page page);
}