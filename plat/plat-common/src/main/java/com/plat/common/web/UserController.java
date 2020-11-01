package com.plat.common.web;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.entity.User;
import com.plat.common.service.UserService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody User user){
        return userService.save(user);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return userService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody User user) {
		return userService.Update(user);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(User user, Page page) {
		return userService.find(user,page);
	}

	 /**
	 * 用户
	 */
	@PostMapping("/token")
	public Object getUserByToken(HttpServletRequest request) {
		return new BaseResponse<>(200, "success", userService.getUserByToken(request));
	}
}
