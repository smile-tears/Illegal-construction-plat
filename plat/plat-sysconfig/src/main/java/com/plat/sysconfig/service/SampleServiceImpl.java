package com.plat.sysconfig.service;

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
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.sysconfig.dao.SampleRepository;
import com.plat.sysconfig.entity.Sample;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	SampleRepository sampleRepository;

	@Override
	public Object save(Sample sample) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", sampleRepository.save(sample));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		// 删除之前判断是否还有子节点
		for (String id : ids) {
			int size = getSubSample(id).size();
			if (size > 0) {
				return new BaseResponse<>(500, "删除样本列表中含下级样本，请先移除下级样本！");
			}
		}
		sampleRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(Sample target) {
		// TODO Auto-generated method stub
		Optional<Sample> source = sampleRepository.findById(target.getId());
		if (source.isPresent()) {
			int size = getSubSample(target.getId()).size();
			if (size > 0 && (!source.get().getSupSampleId().equals(target.getSupSampleId()))) {
				return new BaseResponse<>(500, "含有下级样本，请先移除下级样本！");
			}
			Sample sample = (Sample) BeanProcessUtils.copy(source.get(), target);
			Sample result = sampleRepository.save(sample);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(Sample sample, Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		sample.setDelTag(1);
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withMatcher("sampleName",
				ExampleMatcher.GenericPropertyMatchers.contains());
		// 创建实例
		Example<Sample> example = Example.of(sample, matcher);
		Long total = sampleRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Integer totalPage = null;
		Object list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() != null) {
			List<Sort.Order> orders = new ArrayList<>();
			orders.add(new Sort.Order(Sort.Direction.ASC, "createTime"));
			Sort sort = Sort.by(orders);
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize(), sort); // pageIndex默认从0开始
			list = sampleRepository.findAll(example, pageable).getContent();
			totalPage = (int) (total % page.getPageSize() == 0 ? total / page.getPageSize()
					: total / page.getPageSize() + 1);
		} else {
			list = sampleRepository.findAll(example);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("data", list);
		result.put("pageNo", page.getPageNo());
		result.put("pageSize", page.getPageSize());
		result.put("totalCount", total);
		if (page.getPageNo() == null || page.getPageSize() == null) {
			result.put("totalPage", 1);
		} else {
			result.put("totalPage", totalPage);
		}
		return new BaseResponse<>(200, "success", result);
	}

	@Override
	public Object tree(String id) {
		// TODO Auto-generated method stub
		JSONArray result = recursion(id, new JSONArray());
		JSONArray children = result.getJSONObject(0).getJSONArray("children");
		if (children.size() > 0 && "根目录".equals(result.getJSONObject(0).getString("title"))) {
			result = children;
		}
		return new BaseResponse<>(200, "success", result);
	}

	public JSONArray recursion(String id, JSONArray jsonArray) {
		List<Sample> list = getSubSample(id);
		for (Sample sample : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", sample.getId());
			jsonObject.put("key", sample.getId());
			jsonObject.put("title", sample.getSampleName());
			jsonObject.put("children", recursion(sample.getId(), new JSONArray()));
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	public List<Sample> getSubSample(String id) {
		Sample sample1 = new Sample();
		sample1.setDelTag(1);
		sample1.setSupSampleId(id);
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		Example<Sample> example = Example.of(sample1, matcher);
		List<Sort.Order> orders = new ArrayList<>();
		orders.add(new Sort.Order(Sort.Direction.ASC, "createTime"));
		Sort sort = Sort.by(orders);
		List<Sample> list = sampleRepository.findAll(example, sort);
		return list;
	}
}
