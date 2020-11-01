package com.plat.sysconfig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

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
import com.plat.common.utils.RedisUtil;
import com.plat.sysconfig.dao.QuestionTypeRepository;
import com.plat.sysconfig.entity.QuestionType;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {

	@Autowired
	QuestionTypeRepository questionTypeRepository;

	@Override
	public Object save(QuestionType questionType) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", questionTypeRepository.save(questionType));
	}
	
	@Resource
    private RedisUtil redisUtil;

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		// 删除之前判断是否还有子节点
		for (String id : ids) {
			int size = getSubQuestionType(id).size();
			if (size > 0) {
				return new BaseResponse<>(500, "删除问题类型列表中含下级问题类型，请先移除下级问题类型！");
			}
		}
		questionTypeRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(QuestionType target) {
		// TODO Auto-generated method stub
		Optional<QuestionType> source = questionTypeRepository.findById(target.getId());
		if (source.isPresent()) {
			int size = getSubQuestionType(target.getId()).size();
			if (size > 0 && (!source.get().getPid().equals(target.getPid()))) {
				return new BaseResponse<>(500, "含有下级问题类型，请先移除下级问题类型！");
			}
			QuestionType questionType = (QuestionType) BeanProcessUtils.copy(source.get(), target);
			QuestionType result = questionTypeRepository.save(questionType);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(QuestionType questionType, Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		questionType.setDelTag(1);
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withMatcher("typeName",
				ExampleMatcher.GenericPropertyMatchers.contains());
		// 创建实例
		Example<QuestionType> example = Example.of(questionType, matcher);
		Long total = questionTypeRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Integer totalPage = null;
		Object list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() != null) {
			List<Sort.Order> orders = new ArrayList<>();
			orders.add(new Sort.Order(Sort.Direction.ASC, "showOrder"));
			Sort sort = Sort.by(orders);
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize(), sort); // pageIndex默认从0开始
			list = questionTypeRepository.findAll(example, pageable).getContent();
			totalPage = (int) (total % page.getPageSize() == 0 ? total / page.getPageSize()
					: total / page.getPageSize() + 1);
		} else {
			list = questionTypeRepository.findAll(example);
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
		Object respnse = null ;
		boolean ok = true;
		try {
			respnse = redisUtil.get("questionTypeTree");
			
		} catch (Exception e) {
			// TODO: handle exception
			ok = false;
			System.out.println("==========redis连接异常==========");
		} finally {
			if (respnse == null) {
				JSONArray result = recursion(id, new JSONArray());
				JSONArray children = result.getJSONObject(0).getJSONArray("children");
				if (children.size() > 0 && "根目录".equals(result.getJSONObject(0).getString("title"))) {
					result = children;
				}
				if (ok) redisUtil.set("questionTypeTree", result);
				respnse = result;
			} 
		}
		
		return new BaseResponse<>(200, "success", respnse);
	}

	public JSONArray recursion(String id, JSONArray jsonArray) {
		List<QuestionType> list = getSubQuestionType(id);
		for (QuestionType questionType : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", questionType.getId());
			jsonObject.put("key", questionType.getId());
			jsonObject.put("title", questionType.getTypeName());
			jsonObject.put("children", recursion(questionType.getId(), new JSONArray()));
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	public List<QuestionType> getSubQuestionType(String id) {
		QuestionType questionType1 = new QuestionType();
		questionType1.setDelTag(1);
		questionType1.setPid(id);
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		Example<QuestionType> example = Example.of(questionType1, matcher);
		List<Sort.Order> orders = new ArrayList<>();
		orders.add(new Sort.Order(Sort.Direction.ASC, "showOrder"));
		Sort sort = Sort.by(orders);
		List<QuestionType> list = questionTypeRepository.findAll(example, sort);
		return list;
	}
}
