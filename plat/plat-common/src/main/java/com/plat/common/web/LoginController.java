package com.plat.common.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.plat.common.dao.UserRepository;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.entity.ResponseStatusEnum;
import com.plat.common.entity.User;
import com.plat.common.service.UserService;
import com.plat.common.utils.JwtUtils;


@RestController
public class LoginController {

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	@PostMapping("/about")
	public String index() {
		//int a = 100 / 0;
		return "这是一个需要登录后才能访问的页面";
	}

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/login")
	public Object userLogin(@RequestBody User user, ServletResponse response) {
		String username = user.getUsername();
		String password = user.getPassword();
		// 获取当前用户主体
		Subject subject = SecurityUtils.getSubject();
		String msg = null;
		boolean loginSuccess = false;
		// 将用户名和密码封装成 UsernamePasswordToken 对象
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			msg = "登录成功。";
			loginSuccess = true;
		} catch (UnknownAccountException uae) { // 账号不存在
			msg = "账号不存在，请检查后重新输入！";
		} catch (IncorrectCredentialsException ice) { // 账号与密码不匹配
			msg = "用户名与密码不匹配，请检查后重新输入！";
		}  catch (AuthenticationException ae) { // 其他身份验证异常
			msg = "登录异常，请联系管理员！"+ae.getMessage();
		}

		if (loginSuccess) {
			String jwtToken = JwtUtils.sign(username, JwtUtils.SECRET);
			User user2 = userService.getUserByUsername(username);
			// 更新注册id registrationID
			if (!StringUtils.isEmpty(user.getRegistrationID())) {
				userRepository.updateRegistrationID(user.getRegistrationID());
			}
			
			// 将签发的 JWT token 设置到 HttpServletResponse 的 Header 中
			((HttpServletResponse) response).setHeader(JwtUtils.AUTH_HEADER, jwtToken);
			return new BaseResponse<>(200,"success",user2);
		} else {
			return new BaseResponse<>(401,"身份认证异常，用户名或密码错误！");
		}
		

	}
}
