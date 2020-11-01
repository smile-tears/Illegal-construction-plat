package com.plat.common.service;

import com.plat.common.entity.User;

import javax.servlet.http.HttpServletRequest;

import com.plat.common.entity.Page;

public interface UserService {
	public Object save(User user);

	public Object deleteByIds(String[] ids);

	public Object Update(User user);

	public Object find(User user, Page page);
	
	public User getUserByUsername (String username);
	
	public User getUserByToken(HttpServletRequest request);
}
