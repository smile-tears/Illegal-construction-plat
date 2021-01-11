package com.plat.caseinfo.service;

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
import com.plat.caseinfo.dao.MessageReceiveRepository;
import com.plat.caseinfo.entity.MessageReceive;

@Service
public class MessageReceiveServiceImpl implements MessageReceiveService {

	@Autowired
	MessageReceiveRepository messageReceiveRepository;

	@Override
	public Object save(MessageReceive messageReceive) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", messageReceiveRepository.save(messageReceive));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		messageReceiveRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

 

	@Override
	public Object find(MessageReceive messageReceive, Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		messageReceive.setDelTag(1);
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withMatcher("messageReceiveName",
				ExampleMatcher.GenericPropertyMatchers.contains());
		// 创建实例
		Example<MessageReceive> example = Example.of(messageReceive, matcher);
		Long total = messageReceiveRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Integer totalPage = null;
		Object list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() != null) {
			List<Sort.Order> orders = new ArrayList<>();
			orders.add(new Sort.Order(Sort.Direction.ASC, "showOrder"));
			Sort sort = Sort.by(orders);
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize(), sort); // pageIndex默认从0开始
			list = messageReceiveRepository.findAll(example, pageable).getContent();
			totalPage = (int) (total % page.getPageSize() == 0 ? total / page.getPageSize()
					: total / page.getPageSize() + 1);
		} else {
			list = messageReceiveRepository.findAll(example);
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
	public Object read(String id) {
		// TODO Auto-generated method stub
		messageReceiveRepository.read(id);
		return new BaseResponse<>(200, "更新阅读状态成功");
	}

}
