package com.plat.caseinfo.service;

import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.CaseInfoArea;

public interface CaseInfoAreaService {
	public Object save(CaseInfoArea caseInfoArea);

	public Object deleteByIds(String[] ids);

	public Object Update(CaseInfoArea caseInfoArea);

	public Object find(CaseInfoArea caseInfoArea, Page page);
	
	public Object find2(int status, Page page,String operator );
}