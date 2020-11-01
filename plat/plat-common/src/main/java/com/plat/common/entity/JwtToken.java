package com.plat.common.entity;


import org.apache.shiro.authc.AuthenticationToken;

import com.plat.common.utils.JwtUtils;



 
public class JwtToken implements AuthenticationToken {
 
	private static final long serialVersionUID = 1L;
 
	// 加密后的 JWT token串
	private String token;
 
	private String userName;
 
	public JwtToken(String token) {
		this.token = token;
		this.userName = JwtUtils.getClaimFiled(token, "username");
	}
 
	@Override
	public Object getPrincipal() {
		return this.userName;
	}
   
	/**
	 *  定义token实体类，把token赋值给Credentials
	 */
	@Override
	public Object getCredentials() {
		return this.token;
	}
}