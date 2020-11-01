package com.plat.common.jwt;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.plat.common.utils.JwtUtils;


/**
 * @author 19093
 *   跟 ShiroRealm 不一样，JwtRealm 不需要拿传入的 JwtToken 和其他的 Token 去做比对，只需验证JwtToken自身的内容是否合法即可。
 *   所以，我们需要为 JwtRealm 自定义一个 CredentialsMatcher 实现。
 */
public class JwtCredentialsMatcher implements CredentialsMatcher {

	/**
	 * JwtCredentialsMatcher只需验证JwtToken内容是否合法
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
		// TODO Auto-generated method stub
		String token = authenticationToken.getCredentials().toString();
		String username = authenticationToken.getPrincipal().toString();
		return JwtUtils.verify(token, username, JwtUtils.SECRET);
	}

}
