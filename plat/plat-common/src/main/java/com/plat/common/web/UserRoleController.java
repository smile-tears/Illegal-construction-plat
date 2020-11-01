package com.plat.common.web;
import com.plat.common.entity.Page;
import com.plat.common.entity.UserMenu;
import com.plat.common.entity.UserRole;
import com.plat.common.service.UserRoleService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_Role")
public class UserRoleController {

    @Autowired
    private UserRoleService user_RoleService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody Iterable<UserRole> entities){
        return user_RoleService.save(entities);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return user_RoleService.deleteByIds(ids);
    }
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(String roleId,String name,Integer pageNo,Integer pageSize) {
		return user_RoleService.find(roleId,name,pageNo,pageSize);
	}
}
