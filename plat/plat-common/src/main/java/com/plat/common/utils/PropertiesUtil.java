package com.plat.common.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author 范嘉恒
 */
public class PropertiesUtil {

    private static Properties _prop;

    /**
     * 读取配置文件
     * @param fileName
     */
    public static void readProperties(String fileName){
        try {
            InputStream in = PropertiesUtil.class.getResourceAsStream("/"+fileName);
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            _prop.load(bf);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 根据key读取对应的value
     * @param key
     * @return
     */
    public static String getProperty(String key){
    	if(_prop == null){
    		_prop = new Properties();
    		readProperties("config/config.properties");
    	}
        return _prop.getProperty(key);
    }
    
}
