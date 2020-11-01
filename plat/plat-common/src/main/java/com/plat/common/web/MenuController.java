package com.plat.common.web;

import com.plat.common.entity.Page;
import com.plat.common.entity.Menu;
import com.plat.common.service.MenuService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * 新增
	 */
	@PostMapping("/post")
	public Object save(@RequestBody Menu menu) {
		return menuService.save(menu);
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public Object deleteById(@RequestBody String[] ids) {
		return menuService.deleteByIds(ids);
	}

	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update(@RequestBody Menu menu) {
		return menuService.Update(menu);
	}

	/**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(Menu menu, Page page) {
		return menuService.find(menu, page);
	}

	/**
	 * 获取菜单树
	 */
	@PostMapping("/tree")
	public Object getTree(@RequestParam(value = "id", defaultValue = "0") String id) {
		return menuService.tree(id);
	}
	
	/**
	 * 获取用户菜单树
	 */
	@PostMapping("/user-tree")
	public Object getUserTree(String userId,@RequestParam(value = "id", defaultValue = "0") String id) {
		return menuService.userTree(userId, id);
	}

}
