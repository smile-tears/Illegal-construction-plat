package com.plat.sysconfig.service;

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
import com.plat.sysconfig.dao.DictionaryDetailRepository;
import com.plat.sysconfig.entity.DictionaryDetail;

@Service
public class DictionaryDetailServiceImpl implements DictionaryDetailService {

	@Autowired
	DictionaryDetailRepository dictionaryDetailRepository;

	@Override
	public Object save(DictionaryDetail dictionaryDetail) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", dictionaryDetailRepository.save(dictionaryDetail));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		dictionaryDetailRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(DictionaryDetail target) {
		// TODO Auto-generated method stub
		Optional<DictionaryDetail> source = dictionaryDetailRepository.findById(target.getId());
		if (source.isPresent()) {
			DictionaryDetail dictionaryDetail = (DictionaryDetail) BeanProcessUtils.copy(source, target);
			DictionaryDetail result = dictionaryDetailRepository.save(dictionaryDetail);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(DictionaryDetail dictionaryDetail, Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		dictionaryDetail.setDelTag(1);
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		// 创建实例
		Example<DictionaryDetail> example = Example.of(dictionaryDetail, matcher);
		Long total = dictionaryDetailRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Object list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() !=null ) {
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize()); // getPageNo默认从0开始
			list = dictionaryDetailRepository.findAll(example, pageable).getContent();
		} else {
			list = dictionaryDetailRepository.findAll(example);
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
