package com.plat.caseinfo.service;

import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.CaseInfoStreet;

public interface CaseInfoStreetService {
	public Object save(CaseInfoStreet caseInfoStreet);

	public Object deleteByIds(String[] ids);

	public Object Update(CaseInfoStreet caseInfoStreet);

	public Object find(CaseInfoStreet caseInfoStreet, Page page);
	
	public Object find2(int status, Page page,String operator );
}