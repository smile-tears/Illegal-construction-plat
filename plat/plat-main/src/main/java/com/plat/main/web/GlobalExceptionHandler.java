package com.plat.main.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.ResponseStatusEnum;

//@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public BaseResponse<Object> exception (HttpServletRequest request,HttpServletResponse response,Exception e) {
		BaseResponse<Object> ret = new BaseResponse<Object>();
		ret.setCode(ResponseStatusEnum.Internal_Server_Error.getCode());
		ret.setMessage(e.getMessage());
		Logger logger = Logger.getLogger(getClass());
		logger.error("异常信息："+e.getMessage());
		return ret;
		
	}
}
