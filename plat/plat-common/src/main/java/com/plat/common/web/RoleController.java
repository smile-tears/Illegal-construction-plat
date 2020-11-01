package com.plat.common.web;
import com.plat.common.entity.Page;
import com.plat.common.entity.Role;
import com.plat.common.service.RoleService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody Role role){
        return roleService.save(role);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return roleService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody Role role) {
		return roleService.Update(role);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(Role role, Page page) {
		return roleService.find(role,page);
	}
}
