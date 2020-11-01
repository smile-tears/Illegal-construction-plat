package com.plat.common.service;

import com.plat.common.entity.UserRole;

public interface UserRoleService {
	public Object save(Iterable<UserRole> entities);

	public Object deleteByIds(String[] ids);

	public Object find(String roleId,String name,Integer pageNo,Integer pageSize);
}