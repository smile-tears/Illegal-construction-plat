package com.plat.common.service;

import java.util.List;

import com.plat.common.entity.Page;
import com.plat.common.entity.SubCompany;

public interface SubCompanyService {
	public Object save(SubCompany subCompany);

	public Object deleteByIds(String[] ids);

	public Object Update(SubCompany subCompany);

	public Object find(SubCompany subCompany, Page page);
	
	public List<SubCompany> getChildrenSubCompanyId(String id);
}