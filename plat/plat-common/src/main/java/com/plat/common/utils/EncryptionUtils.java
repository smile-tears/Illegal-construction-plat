package com.plat.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

public class EncryptionUtils {
	public static final String algorithmName = "md5";//算法
	public static final String salt = "oa.system";//盐
	public static final int hashIterations = 1;//迭代次数
	/**
	 * 调用org.apache.shiro.crypto.hash.SimpleHash.SimpleHash(String algorithmName, Object source, Object salt, int hashIterations)
	 * 构造方法实现盐值加密  String algorithmName 为加密算法 支持md5 base64 等
	 * @param source 明文
	 * @return       密文
	 */
	public static String encrypt(String source) {
        SimpleHash sh = new SimpleHash(algorithmName, source, salt, hashIterations);
		return sh.toHex();
	}
	
	public static void main(String[] args) {
		System.out.println(encrypt("123"));
	}
}
