package com.plat.sysconfig.service;

import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.DictionaryDetail;

public interface DictionaryDetailService {
	public Object save(DictionaryDetail dictionaryDetail);

	public Object deleteByIds(String[] ids);

	public Object Update(DictionaryDetail dictionaryDetail);

	public Object find(DictionaryDetail dictionaryDetail, Page page);
}
