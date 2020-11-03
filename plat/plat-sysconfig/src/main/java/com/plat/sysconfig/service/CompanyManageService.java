package com.plat.sysconfig.service;

import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.CompanyManage;

public interface CompanyManageService {
	public Object save(CompanyManage companyManage);

	public Object deleteByIds(String[] ids);

	public Object Update(CompanyManage companyManage);

	public Object find(CompanyManage companyManage, Page page);
	
}