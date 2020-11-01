package com.plat.common.service;

import com.plat.common.entity.Page;
import com.plat.common.entity.WorkInfo;

public interface WorkInfoService {
	public Object save(WorkInfo workInfo);

	public Object deleteByIds(String[] ids);

	public Object Update(WorkInfo workInfo);

	public Object find(WorkInfo workInfo, Page page);
}