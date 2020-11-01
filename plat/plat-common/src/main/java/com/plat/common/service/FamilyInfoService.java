package com.plat.common.service;

import com.plat.common.entity.Page;
import com.plat.common.entity.FamilyInfo;

public interface FamilyInfoService {
	public Object save(FamilyInfo familyInfo);

	public Object deleteByIds(String[] ids);

	public Object Update(FamilyInfo familyInfo);

	public Object find(FamilyInfo familyInfo, Page page);
}