package com.plat.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.common.dao.MenuRepository;
import com.plat.common.entity.Menu;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuRepository menuRepository;

	@Override
	public Object save(Menu menu) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", menuRepository.save(menu));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		// 删除之前判断是否还有子节点
		for (String id : ids) {
			int size = getSubMenu(id).size();
			if (size > 0) {
				return new BaseResponse<>(500, "删除菜单列表中含下级菜单，请先移除下级菜单！");
			}
		}
		menuRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(Menu target) {
		// TODO Auto-generated method stub
		Optional<Menu> source = menuRepository.findById(target.getId());
		if (source.isPresent()) {
			int size = getSubMenu(target.getId()).size();
			if (size > 0 && (!source.get().getSupMenuid().equals(target.getSupMenuid()))) {
				return new BaseResponse<>(500, "含有下级菜单，请先移除下级菜单！");
			}
			Menu menu = (Menu) BeanProcessUtils.copy(source.get(), target);
			Menu result = menuRepository.save(menu);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(Menu menu, Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		menu.setDelTag(1);
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withMatcher("menuName",
				ExampleMatcher.GenericPropertyMatchers.contains());
		// 创建实例
		Example<Menu> example = Example.of(menu, matcher);
		Long total = menuRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Integer totalPage = null;
		Object list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() != null) {
			List<Sort.Order> orders = new ArrayList<>();
			orders.add(new Sort.Order(Sort.Direction.ASC, "showOrder"));
			Sort sort = Sort.by(orders);
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize(), sort); // pageIndex默认从0开始
			list = menuRepository.findAll(example, pageable).getContent();
			totalPage = (int) (total % page.getPageSize() == 0 ? total / page.getPageSize()
					: total / page.getPageSize() + 1);
		} else {
			list = menuRepository.findAll(example);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("data", list);
		result.put("pageNo", page.getPageNo());
		result.put("pageSize", page.getPageSize());
		result.put("totalCount", total);
		if (page.getPageNo() == null || page.getPageSize() == null) {
			result.put("totalPage", 1);
		} else {
			result.put("totalPage", totalPage);
		}
		return new BaseResponse<>(200, "success", result);
	}

	@Override
	public Object tree(String id) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", recursion(id, new JSONArray()));
	}

	public JSONArray recursion(String id, JSONArray jsonArray) {
		List<Menu> list = getSubMenu(id);
		for (Menu menu : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", menu.getId());
			jsonObject.put("key", menu.getId());
			jsonObject.put("title", menu.getMenuName());
			jsonObject.put("children", recursion(menu.getId(), new JSONArray()));
			jsonObject.put("icon", menu.getIcon());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	public List<Menu> getSubMenu(String id) {
		Menu menu1 = new Menu();
		menu1.setDelTag(1);
		menu1.setSupMenuid(id);
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		Example<Menu> example = Example.of(menu1, matcher);
		List<Sort.Order> orders = new ArrayList<>();
		orders.add(new Sort.Order(Sort.Direction.ASC, "showOrder"));
		Sort sort = Sort.by(orders);
		List<Menu> list = menuRepository.findAll(example, sort);
		return list;
	}

	@Override
	public Object userTree(String userId, String supMenuid) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", userTreeRecursion(userId, supMenuid, new JSONArray()));
	}

//	public JSONArray userTreeRecursion(String userId, String supMenuid, JSONArray jsonArray) {
//		List<Menu> menus = menuRepository.getUserMenusBySupMenuId(userId, supMenuid);
//		for (Menu menu : menus) {
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("value", menu.getId());
//			jsonObject.put("key", menu.getId());
//			jsonObject.put("title", menu.getMenuName());
//			jsonObject.put("children", userTreeRecursion(userId, menu.getId(), new JSONArray()));
//			jsonObject.put("icon", menu.getIcon());
//			jsonArray.add(jsonObject);
//		}
//		return jsonArray;
//	}
	public JSONArray userTreeRecursion(String userId, String supMenuid, JSONArray jsonArray) {
		List<Menu> menus = menuRepository.getUserMenusBySupMenuId(userId, supMenuid);
		for (Menu menu : menus) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", menu.getName());
			jsonObject.put("path", menu.getMenuLink());
			jsonObject.put("parentId", menu.getSupMenuid());
			jsonObject.put("id", menu.getId());
			JSONObject meta = new JSONObject();
			meta.put("icon", menu.getIcon());
			meta.put("title", menu.getMenuName());
			meta.put("show", true);
			jsonObject.put("meta", meta);
			jsonObject.put("component", menu.getComponent());
			jsonObject.put("redirect", menu.getRedirect());
			jsonArray.add(jsonObject);
			userTreeRecursion(userId, menu.getId(), jsonArray);
		}
		return jsonArray;
	}
}
