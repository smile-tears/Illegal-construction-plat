package com.plat.sysconfig.service;

import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.SysGlobalConfig;

public interface SysGlobalConfigService {
	public Object save(SysGlobalConfig sysGlobalConfig);

	public Object Update(SysGlobalConfig sysGlobalConfig);

	public Object find(SysGlobalConfig sysGlobalConfig);
}