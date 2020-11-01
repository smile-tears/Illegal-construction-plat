package com.plat.common.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.*;

public class BeanProcessUtils {
	
	public static Object copy(Object source,Object target) {
		BeanUtils.copyProperties(source, target, getNoNullProperties(target));
		return target;
	}
	
	/**
     * @param target 目标源数据
     * @return 将目标源中不为空的字段取出
     */
	private static String[] getNoNullProperties(Object target) {
        BeanWrapper srcBean = new BeanWrapperImpl(target);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> noEmptyName = new HashSet<>();
        for (PropertyDescriptor p : pds) {
            Object value = srcBean.getPropertyValue(p.getName());
            if (value != null) noEmptyName.add(p.getName());
        }
        String[] result = new String[noEmptyName.size()];
        return noEmptyName.toArray(result);
    }
}
