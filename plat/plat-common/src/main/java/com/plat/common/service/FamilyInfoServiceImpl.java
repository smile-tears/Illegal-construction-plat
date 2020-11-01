package com.plat.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.common.dao.FamilyInfoRepository;
import com.plat.common.entity.FamilyInfo;

@Service
public class FamilyInfoServiceImpl implements FamilyInfoService {

	@Autowired
	FamilyInfoRepository familyInfoRepository;

	@Override
	public Object save(FamilyInfo familyInfo) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", familyInfoRepository.save(familyInfo));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		familyInfoRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(FamilyInfo target) {
		// TODO Auto-generated method stub
		Optional<FamilyInfo> source = familyInfoRepository.findById(target.getId());
		if (source.isPresent()) {
			FamilyInfo familyInfo = (FamilyInfo) BeanProcessUtils.copy(source.get(), target);
			FamilyInfo result = familyInfoRepository.save(familyInfo);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(FamilyInfo familyInfo,Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
		.withMatcher("name" ,ExampleMatcher.GenericPropertyMatchers.contains())
		.withMatcher("title" ,ExampleMatcher.GenericPropertyMatchers.contains())
		.withMatcher("contactNum" ,ExampleMatcher.GenericPropertyMatchers.contains())
		.withMatcher("address" ,ExampleMatcher.GenericPropertyMatchers.contains())
		;
		// 创建实例
		Example<FamilyInfo> example = Example.of(familyInfo, matcher);
		Long total = familyInfoRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Object list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() !=null ) {
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize()); // pageIndex默认从0开始
			list = familyInfoRepository.findAll(example, pageable).getContent();
		} else {
			list = familyInfoRepository.findAll(example);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("data", list);
		result.put("pageNo", page.getPageNo()); 
		result.put("pageSize", page.getPageSize());
		result.put("totalCount", total);
		if (page.getPageNo() == null || page.getPageSize() ==null) {
			result.put("totalPage",1);
		} else {
			result.put("totalPage", total % page.getPageSize() == 0 ? total / page.getPageSize() : total / page.getPageSize() + 1);
		}
		return new BaseResponse<>(200, "success", result);
	}

}
