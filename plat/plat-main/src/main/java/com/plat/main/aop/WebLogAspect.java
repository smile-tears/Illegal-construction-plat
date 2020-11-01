package com.plat.main.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.plat.common.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
 
//@Aspect
//@Order(0)
//@Component
public class WebLogAspect {
 
    ThreadLocal<Long> startTime = new ThreadLocal<>();
 
    private Logger logger = Logger.getLogger(getClass());
 
	@Pointcut ("execution(public * *.plat.*.web..*.*(..))")
	/* ("execution(public * com.example.controller..*.*(..))") */
    public void webLog() {
    }
 
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            //请求用户
            String username = "";
            if (request.getHeader(JwtUtils.AUTH_HEADER) != null) {
            	username = JwtUtils.getClaimFiled(request.getHeader(JwtUtils.AUTH_HEADER), "username");
            }
            // 记录下请求内容
            System.out.println("\r\n");
            logger.info("----------------start-----------------");            
            logger.info("客户端IP : " + request.getRemoteAddr());
            logger.info("用户 : " + username);
            logger.info("请求地址 : " + request.getRequestURL().toString());
            logger.info("请求方式 : " + request.getMethod());
            logger.info("执行的方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            Object[] args = joinPoint.getArgs().clone();
            logger.info("请求参数 : " + Arrays.toString(args));
            //logger.info("请求参数 : " + JSON.toJSONString(args));
            
        }
    }
 
    /**
             *处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        logger.info("返回内容 : " + ret);
        logger.info("请求耗时 : " + (System.currentTimeMillis() - startTime.get()) + "毫秒");
        logger.info("----------------end-----------------");
    }
}
