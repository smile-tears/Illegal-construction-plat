package com.plat.common.service;

import com.plat.common.entity.Page;
import com.plat.common.entity.JobMask;

public interface JobMaskService {
	public Object save(JobMask jobMask);

	public Object deleteByIds(String[] ids);

	public Object Update(JobMask jobMask);

	public Object find(JobMask jobMask, Page page);
}