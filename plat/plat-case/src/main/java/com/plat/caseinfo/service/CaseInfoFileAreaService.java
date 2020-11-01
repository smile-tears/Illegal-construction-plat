package com.plat.caseinfo.service;

import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.CaseInfoFileArea;

public interface CaseInfoFileAreaService {
	public Object save(CaseInfoFileArea caseInfoFileArea);

	public Object deleteByIds(String[] ids);

	public Object Update(CaseInfoFileArea caseInfoFileArea);

	public Object find(CaseInfoFileArea caseInfoFileArea, Page page);
}