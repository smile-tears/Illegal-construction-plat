package com.plat.common.service;

import java.util.List;

import com.plat.common.entity.Page;
import com.plat.common.entity.UserMenu;

public interface UserMenuService {
	public Object save(Iterable<UserMenu> entities);
	
	public Object getMenuIdsByUserId (String userId);
}