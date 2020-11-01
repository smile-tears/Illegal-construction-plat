package com.plat.common.jwt;
import java.io.PrintWriter;
 
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.JwtToken;
import com.plat.common.entity.ResponseStatusEnum;
import com.plat.common.utils.JwtUtils;
 
/**
 * 自定义的认证过滤器，用来拦截Header中携带 JWT token的请求
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {
 
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
      //无条件放行OPTIONS
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
        	//this.fillCorsHeader(httpRequest, httpResponse);
            return true;
        }
        return super.preHandle(request, response);
    }
	/**
	 * 增加跨域支持
	 */
	@Override
    protected void postHandle(ServletRequest request, ServletResponse response){
        //this.fillCorsHeader(WebUtils.toHttp(request), WebUtils.toHttp(response));
    }
	
	/**
	 * 过滤器拦截请求的入口方法 
	 * 返回 true 则允许访问 
	 * 返回false 则禁止访问，会进入 onAccessDenied()
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (this.isLoginRequest(request, response))
			return true;
		boolean allowed = false;
		try {
			//此处写个白名单，让websocket暂时绕过token校验
//			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//			String uri = httpServletRequest.getRequestURI();
//			System.out.println("uri:" + uri);
//			if(uri.startsWith("/websocket")){
//				allowed = true;
//			}else{
				allowed = executeLogin(request, response);
//			}

		} catch (IllegalStateException e) { // not found any token
			log.error("Not found any token");
		} catch (Exception e) {
			log.error("Error occurs when login", e);
		}
		return allowed || super.isPermissive(mappedValue);
	}

	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        try {
            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
        	System.out.println("===jwt认证异常"+e.getMessage());
            return onLoginFailure(token, e, request, response);
        }
    }
	/**
	 * 这里重写了父类的方法，使用我们自己定义的Token类，提交给shiro。这个方法返回null的话会直接抛出异常，进入isAccessAllowed（）的异常处理逻辑。
	 */
	@Override
	protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		String jwtToken = httpServletRequest.getHeader(JwtUtils.AUTH_HEADER);//得到请求体中的header
		//System.out.println(jwtToken);
		if (jwtToken!=null && !JwtUtils.isTokenExpired(jwtToken)) {//判断token是否过期
			return new JwtToken(jwtToken);
		}
		return null;
	}
 
	/**
	 * isAccessAllowed()方法返回false，会进入该方法，表示拒绝访问
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		HttpServletResponse httpResponse = WebUtils.toHttp(servletResponse);
		httpResponse.setCharacterEncoding("UTF-8");
		httpResponse.setContentType("application/json;charset=UTF-8");
		httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());//认证失败
		PrintWriter writer = httpResponse.getWriter();
		BaseResponse<Object> ret = new BaseResponse<Object>();
		ResponseStatusEnum enum1 = ResponseStatusEnum.UNAUTHORIZED;
		ret.setCode(enum1.getCode());
		ret.setMessage(enum1.getMsg());
		writer.write(JSON.toJSONString(ret));//认证失败
		//fillCorsHeader(WebUtils.toHttp(servletRequest), httpResponse);
		return false;
	}
 
	/**
	 * Shiro 利用 JWT token 登录成功，会进入该方法
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		HttpServletResponse httpResponse = WebUtils.toHttp(response);
		String newToken = null;
		if (token instanceof JwtToken) {
			//此时的token.getCredentials() 已经被JwtToken赋过值了
			newToken = JwtUtils.refreshTokenExpired(token.getCredentials().toString(), JwtUtils.SECRET);
		}
		if (newToken != null)
			httpResponse.setHeader(JwtUtils.AUTH_HEADER, newToken);
		return true;
	}
 
	/**
	 * Shiro 利用 JWT token 登录失败，会进入该方法
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		return false;
	}
 
	/**
	 * 添加跨域支持
	 */
//	protected void fillCorsHeader(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//		httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
//		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,HEAD");
//		httpServletResponse.setHeader("Access-Control-Allow-Headers",
//		httpServletRequest.getHeader("Access-Control-Request-Headers"));
//	}

}