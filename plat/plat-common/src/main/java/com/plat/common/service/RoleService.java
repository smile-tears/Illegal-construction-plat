package com.plat.common.service;

import com.plat.common.entity.Page;
import com.plat.common.entity.Role;

public interface RoleService {
	public Object save(Role role);

	public Object deleteByIds(String[] ids);

	public Object Update(Role role);

	public Object find(Role role, Page page);
}