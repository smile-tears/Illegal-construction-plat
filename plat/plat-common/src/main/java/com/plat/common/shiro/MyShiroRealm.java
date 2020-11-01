package com.plat.common.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.plat.common.entity.Page;
import com.plat.common.entity.User;
import com.plat.common.service.UserService;
import com.plat.common.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	//设置盐解析，这里要和初始化密码设置相同，使用MD5，解密次数1次
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		//HashedCredentialsMatcher是shiro提供的解析盐的实现类 
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName(EncryptionUtils.algorithmName);
		matcher.setHashIterations(EncryptionUtils.hashIterations);
		super.setCredentialsMatcher(matcher);
	}	
	/**
	 * 限定这个 Realm 只处理 UsernamePasswordToken
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		String s = arg0.getPrimaryPrincipal().toString();
		System.out.println(s.toString());
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		simpleAuthorInfo.addStringPermission("admin");// 给当前用户授权url为hello的权限码
		System.out.println("经试验：并不是每次调用接口就会执行，而是调用需要操作码（permission）的接口就会执行");
		return simpleAuthorInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		// 从 AuthenticationToken 中获取当前用户
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		User user = userService.getUserByUsername(username);
		System.out.println("======登陆认证======"+username);
		// 用户不存在
		if (user == null) throw new UnknownAccountException("用户不存在！");
		// 使用盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(EncryptionUtils.salt);
		return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), credentialsSalt, getName());

	}
}
