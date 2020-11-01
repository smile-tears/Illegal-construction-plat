package com.plat.common.jwt;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.plat.common.entity.JwtToken;
import com.plat.common.entity.User;


/**
* JwtRealm 只负责校验 JwtToken
*/
public class JwtRealm extends AuthorizingRealm {

	/**
	 * 限定这个 Realm 只处理我们自定义的 JwtToken
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		// TODO Auto-generated method stub
		return token instanceof JwtToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		// 获取当前用户
		User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
		// 查询数据库，获取用户的角色信息
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		Set<String> roles = ShiroRealm.roleMap.get(currentUser.getName());
//		// 查询数据库，获取用户的权限信息
//		Set<String> perms = ShiroRealm.permMap.get(currentUser.getName());
//		
//		info.setRoles(roles);
//		info.setStringPermissions(perms);

		return info;
	}

	/**
	 * 此处的 SimpleAuthenticationInfo 可返回任意值，密码校验时不会用到它
	*/
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		JwtToken jwtToken = (JwtToken) token;
		// 从 JwtToken 中获取当前用户
		String username = (String) jwtToken.getPrincipal();
		// 查询数据库获取用户信息，
		User user = new User();
		user.setUsername(username);
		user.setPassword("36e9740c2a79ea71e1493eaaea4003fa");

		// 用户不存在
		if (user == null)
			throw new UnknownAccountException("用户不存在！");

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, username, getName());
		return info;
	}

}
