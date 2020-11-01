package com.plat.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.common.dao.JobMaskRepository;
import com.plat.common.entity.JobMask;

@Service
public class JobMaskServiceImpl implements JobMaskService {

	@Autowired
	JobMaskRepository jobMaskRepository;

	@Override
	public Object save(JobMask jobMask) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", jobMaskRepository.save(jobMask));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		jobMaskRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(JobMask target) {
		// TODO Auto-generated method stub
		Optional<JobMask> source = jobMaskRepository.findById(target.getId());
		if (source.isPresent()) {
			JobMask jobMask = (JobMask) BeanProcessUtils.copy(source.get(), target);
			JobMask result = jobMaskRepository.save(jobMask);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(JobMask jobMask, Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		jobMask.setDelTag(1);
		// 创建匹配器，需要查询条件请修改此处代码
		//ExampleMatcher matcher = ExampleMatcher.matchingAll();
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withMatcher("jobName", ExampleMatcher.GenericPropertyMatchers.contains());
		// 创建实例
		Example<JobMask> example = Example.of(jobMask, matcher);
		Long total = jobMaskRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Object list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() != null) {
			List<Sort.Order> orders = new ArrayList<>();
			orders.add(new Sort.Order(Sort.Direction.ASC,"showOrder"));
			Sort sort = Sort.by(orders);
			pageable=PageRequest.of(page.getPageNo() - 1, page.getPageSize(), sort);
			list = jobMaskRepository.findAll(example, pageable).getContent();
		} else {
			list = jobMaskRepository.findAll(example);
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
