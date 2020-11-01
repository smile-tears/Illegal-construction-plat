package com.plat.sysconfig.service;

import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.Sample;

public interface SampleService {
	public Object save(Sample sample);

	public Object deleteByIds(String[] ids);

	public Object Update(Sample sample);

	public Object find(Sample sample, Page page);
	
	public Object tree(String id);
}