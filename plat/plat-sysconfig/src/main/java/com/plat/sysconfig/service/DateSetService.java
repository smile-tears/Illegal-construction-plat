package com.plat.sysconfig.service;

import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.DateSet;

public interface DateSetService {
	public Object save(DateSet dateSet);

	public Object deleteByIds(String[] ids);

	public Object Update(DateSet dateSet);

	public Object find(DateSet dateSet, Page page);
}