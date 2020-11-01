package com.plat.common.service;

import java.util.List;

import com.plat.common.entity.Page;
import com.plat.common.entity.RoleMenu;

public interface RoleMenuService {
	public Object save(Iterable<RoleMenu> entities);

	public Object deleteByRoleId (String roleId);

	public Object getMenuIdsByRoleId (String roleId);
}