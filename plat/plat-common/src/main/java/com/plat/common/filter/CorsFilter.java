package com.plat.common.filter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


/**
 * @author 19093
 * 跨域filter
 */
@Component
public class CorsFilter implements Filter{

	private  final List<String> allowOrigins = Arrays.asList("http://localhost:8000");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String curOrigin = httpServletRequest.getHeader("Origin");
		System.out.println("###跨域过滤器->当前访问来源->"+curOrigin+"###");
		httpServletResponse.setHeader("Access-Control-Allow-Origin", allowOrigins.contains(curOrigin)? curOrigin : "*");
//		if(curOrigin.indexOf("localhost:8000")>-1){
//		}
//		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//		httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");  
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");  
        System.out.println("*********************************跨域过滤器被使用**************************");

        chain.doFilter(request, response); 
	}

}
