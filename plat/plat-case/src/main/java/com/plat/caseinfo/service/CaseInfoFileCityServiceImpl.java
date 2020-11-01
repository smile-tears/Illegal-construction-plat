package com.plat.caseinfo.service;

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
import com.plat.caseinfo.dao.CaseInfoFileCityRepository;
import com.plat.caseinfo.entity.CaseInfoFileCity;

@Service
public class CaseInfoFileCityServiceImpl implements CaseInfoFileCityService {

	@Autowired
	CaseInfoFileCityRepository caseInfoFileCityRepository;

	@Override
	public Object save(CaseInfoFileCity caseInfoFileCity) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", caseInfoFileCityRepository.save(caseInfoFileCity));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		caseInfoFileCityRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(CaseInfoFileCity target) {
		// TODO Auto-generated method stub
		Optional<CaseInfoFileCity> source = caseInfoFileCityRepository.findById(target.getId());
		if (source.isPresent()) {
			CaseInfoFileCity caseInfoFileCity = (CaseInfoFileCity) BeanProcessUtils.copy(source.get(), target);
			CaseInfoFileCity result = caseInfoFileCityRepository.save(caseInfoFileCity);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(CaseInfoFileCity caseInfoFileCity,Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		// 创建实例
		Example<CaseInfoFileCity> example = Example.of(caseInfoFileCity, matcher);
		Long total = caseInfoFileCityRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Object list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() !=null ) {
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize()); // pageIndex默认从0开始
			list = caseInfoFileCityRepository.findAll(example, pageable).getContent();
		} else {
			list = caseInfoFileCityRepository.findAll(example);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("data", list);
		result.put("pageNo", page.getPageNo()); 
		result.put("pageSize", page.getPageSize());
		result.put("totalCount", total);
		result.put("totalPage", total % page.getPageSize() == 0 ? total / page.getPageSize() : total / page.getPageSize() + 1);
		return new BaseResponse<>(200, "success", result);
	}

}
