package com.plat.common.service;


import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.dao.RoleMenuRepository;
import com.plat.common.entity.RoleMenu;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

	@Autowired
	RoleMenuRepository role_MenuRepository;

	@Override
	public Object save(Iterable<RoleMenu> entities) {
		// TODO Auto-generated method stub
		Iterator<RoleMenu> iterator = entities.iterator();
		if (iterator.hasNext() ) {
			String roleId = iterator.next().getRoleId();
			role_MenuRepository.deleteByRoleId(roleId);
		}
		role_MenuRepository.saveAll(entities);
		return new BaseResponse<>(200, "保存成功");
	}

	@Override
	public Object deleteByRoleId(String roleId) {
		// TODO Auto-generated method stub
		role_MenuRepository.deleteByRoleId(roleId);
		return new BaseResponse<>(200, "删除成功");
	}

	public Object getMenuIdsByRoleId (String roleId) {
		return new BaseResponse<>(200, "success", role_MenuRepository.getMenuIdsByRoleId(roleId));
	}

}
