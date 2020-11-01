package com.plat.common.entity;

public enum ResponseStatusEnum {
	
	OK(200,"Success"),
	UNAUTHORIZED(401,"未认证"),
	ACCOUNT_NOT_EXIEST(300,"用户不存在"),
	PAGE_NOT_FOUND(404,"页面未找到"),
	Internal_Server_Error(500,"内部服务错误");
	
	private Integer code;
    private String msg;
    private ResponseStatusEnum (Integer code,String msg) {
		this.code = code;
		this.msg= msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
