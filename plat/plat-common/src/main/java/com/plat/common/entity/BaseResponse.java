package com.plat.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;

import javassist.expr.NewArray;

public class BaseResponse<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int code;
	private String message;
	private T result;
	private Long timestamp;
	

	public BaseResponse() {
		
	}
	public BaseResponse(int code,String message) {
		this.code = code;
		this.message = message;
	}
	public BaseResponse(int code,String message,T result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public Long getTimestamp() {
		return new Date().getTime();
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
