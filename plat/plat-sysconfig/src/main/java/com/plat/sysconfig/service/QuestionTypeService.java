package com.plat.sysconfig.service;

import java.util.List;
import java.util.Map;

import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.QuestionType;

public interface QuestionTypeService {
	public Object save(QuestionType questionType);

	public Object deleteByIds(String[] ids);

	public Object Update(QuestionType questionType);

	public Object find(QuestionType questionType, Page page);
	
	public Object getFstLvlType();
	public Object getSecLvlType(String[] fstLvlIds);
}