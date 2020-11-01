package com.plat.common.shiro;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.plat.common.jwt.JwtCredentialsMatcher;
import com.plat.common.jwt.JwtFilter;
import com.plat.common.jwt.JwtRealm;




@Configuration
public class ShiroConfig {

	/**
	 * 交由 Spring 来自动地管理 Shiro-Bean 的生命周期
	 */
	@Bean
	public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 为 Spring-Bean 开启对 Shiro 注解的支持
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator app = new DefaultAdvisorAutoProxyCreator();
		app.setProxyTargetClass(true);
		return app;
	}

	/**
	 * 配置访问资源需要的权限
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");// 登陆成功后会跳转到该路径，前提是处理登录的请求必须是post
		shiroFilterFactoryBean.setUnauthorizedUrl("/403"); // 未授权界面;

		// 添加jwt过滤器 将所有请求交由jwtFilter 处理
		Map<String, Filter> filterMap = new LinkedHashMap<>();
		filterMap.put("jwtFilter", jwtFilter());
		shiroFilterFactoryBean.setFilters(filterMap);

		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/redis/**", "anon");
		filterChainDefinitionMap.put("/jPush/**", "anon");
		filterChainDefinitionMap.put("/websocket/**", "anon");
		filterChainDefinitionMap.put("/testws/**", "anon");
		filterChainDefinitionMap.put("/upload-avatar", "anon");// 测试
		filterChainDefinitionMap.put("/upload", "anon");
		filterChainDefinitionMap.put("/avatar/**", "anon");
		filterChainDefinitionMap.put("/file/**", "anon");
		filterChainDefinitionMap.put("/hello", "anon");// 测试
		filterChainDefinitionMap.put("/index2", "anon");// 测试
		filterChainDefinitionMap.put("/logs/**", "anon");
		filterChainDefinitionMap.put("/service/**", "anon");
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");

		//静态文件目录
		filterChainDefinitionMap.put("/cityManage/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/loading/**", "anon");
		filterChainDefinitionMap.put("/logo.png", "anon");
		filterChainDefinitionMap.put("/avatar2.jpg", "anon");

		filterChainDefinitionMap.put("/doLogin", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/web/**", "anon");
		filterChainDefinitionMap.put("/wechat/**", "anon");
		filterChainDefinitionMap.put("/tran/**", "anon");
		filterChainDefinitionMap.put("/security/**", "anon");
		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		// 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "jwtFilter,authc");
		// filterChainDefinitionMap.put("/**", "authc");



		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		System.out.println("Shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}

	/**
	 * 配置 ModularRealmAuthenticator
	 */
	@Bean
	public ModularRealmAuthenticator authenticator() {
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		// 设置多 Realm的认证策略，默认 AtLeastOneSuccessfulStrategy
		AuthenticationStrategy strategy = new FirstSuccessfulStrategy();
		authenticator.setAuthenticationStrategy(strategy);
		return authenticator;
	}

	/**
	 * 禁用session, 不保存用户登录状态。保证每次请求都重新认证
	 */
	@Bean
	protected SessionStorageEvaluator sessionStorageEvaluator() {
		DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		sessionStorageEvaluator.setSessionStorageEnabled(false);
		return sessionStorageEvaluator;
	}

	/**
	 * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
	 * 
	 * @return
	 */
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}

	@Bean
	JwtRealm jwtRealm() {
		JwtRealm jwtRealm = new JwtRealm();
		// 设置加密算法
		CredentialsMatcher credentialsMatcher = new JwtCredentialsMatcher();
		jwtRealm.setCredentialsMatcher(credentialsMatcher);
		return jwtRealm;
	}

	public JwtFilter jwtFilter() {
		return new JwtFilter();
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

		// 1.Authenticator
		securityManager.setAuthenticator(authenticator());
		// 2.Realm 多个realm以list数组传递
		List<Realm> realms = new ArrayList<Realm>();
		realms.add(myShiroRealm());
		realms.add(jwtRealm());
		
		securityManager.setRealms(realms);

		// 3.关闭shiro自带的session
		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
		subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator());
		securityManager.setSubjectDAO(subjectDAO);

		return securityManager;
	}
}
