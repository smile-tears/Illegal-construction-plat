package com.plat.common.web;
import com.plat.common.entity.Page;
import com.plat.common.entity.RoleMenu;
import com.plat.common.service.RoleMenuService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role_Menu")
public class RoleMenuController {

    @Autowired
    private RoleMenuService role_MenuService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save( @RequestBody Iterable<RoleMenu> entities){
        return role_MenuService.save(entities);
    }


    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(String roleId) {
		return role_MenuService.getMenuIdsByRoleId(roleId);
	}
}
