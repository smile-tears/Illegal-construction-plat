package com.plat.common.web;
import com.plat.common.entity.Page;
import com.plat.common.entity.UserMenu;
import com.plat.common.service.UserMenuService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userMenu")
public class UserMenuController {

    @Autowired
    private UserMenuService userMenuService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody Iterable<UserMenu> entities){
        return userMenuService.save(entities);
    }
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(String userId) {
		return userMenuService.getMenuIdsByUserId(userId);
	}
}
