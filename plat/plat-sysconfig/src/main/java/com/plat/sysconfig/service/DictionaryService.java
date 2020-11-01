package com.plat.sysconfig.service;

import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.Dictionary;

public interface DictionaryService {
	public Object save(Dictionary dictionary);

	public Object deleteByIds(String[] ids);

	public Object Update(Dictionary dictionary);

	public Object find(Dictionary dictionary, Page page);
}
