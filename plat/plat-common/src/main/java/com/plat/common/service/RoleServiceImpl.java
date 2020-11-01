package com.plat.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.common.dao.RoleRepository;
import com.plat.common.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Object save(Role role) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", roleRepository.save(role));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		roleRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(Role target) {
		// TODO Auto-generated method stub
		Optional<Role> source = roleRepository.findById(target.getId());
		if (source.isPresent()) {
			Role role = (Role) BeanProcessUtils.copy(source.get(), target);
			Role result = roleRepository.save(role);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(Role role, Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		role.setDelTag(1);
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withMatcher("roleName", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("roleDesc", ExampleMatcher.GenericPropertyMatchers.contains());
		// 创建实例
		Example<Role> example = Example.of(role, matcher);
		Long total = roleRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Object list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() != null) {
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize()); // pageIndex默认从0开始
			list = roleRepository.findAll(example, pageable).getContent();
		} else {
			list = roleRepository.findAll(example);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("data", list);
		result.put("pageNo", page.getPageNo());
		result.put("pageSize", page.getPageSize());
		result.put("totalCount", total);
		if (page.getPageNo() == null || page.getPageSize() ==null) {
			result.put("totalPage",1);
		} else {
			result.put("totalPage", total % page.getPageSize() == 0 ? total / page.getPageSize() : total / page.getPageSize() + 1);
		}
		return new BaseResponse<>(200, "success", result);
	}

}
