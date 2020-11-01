package com.plat.common.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.dao.UserMenuRepository;
import com.plat.common.entity.UserMenu;

@Service
public class UserMenuServiceImpl implements UserMenuService {

	@Autowired
	UserMenuRepository userMenuRepository;

	@Override
	public Object save(Iterable<UserMenu> entities) {
		// TODO Auto-generated method stub
		Iterator<UserMenu> iterator = entities.iterator();
		if (iterator.hasNext() ) {
			String userId = iterator.next().getUserId();
			userMenuRepository.deleteByUserId(userId);
		}
		userMenuRepository.saveAll(entities);
		return new BaseResponse<>(200, "保存成功");
	}

	public Object getMenuIdsByUserId (String userId) {
		return new BaseResponse<>(200, "success", userMenuRepository.getMenuIdsByUserId(userId));
	}

}
