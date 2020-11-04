package com.plat.sysconfig.service;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.sysconfig.dao.GridCommunityRepository;
import com.plat.sysconfig.entity.GridCommunity;

import javassist.expr.NewArray;

@Service
public class GridCommunityServiceImpl implements GridCommunityService {

	@Autowired
	GridCommunityRepository gridCommunityRepository;


	@Override
	public Object save(GridCommunity gridCommunity) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", gridCommunityRepository.save(gridCommunity));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		// 删除之前判断是否还有子节点
		for (String id : ids) {
			int size = getSubMenu(id).size();
			if (size > 0) {
				return new BaseResponse<>(500, "删除样本列表中含网格，请先移除下级网格！");
			}
		}
		gridCommunityRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(GridCommunity target) {
		// TODO Auto-generated method stub
		Optional<GridCommunity> source = gridCommunityRepository.findById(target.getId());
		if (source.isPresent()) {
			GridCommunity gridCommunity = (GridCommunity) BeanProcessUtils.copy(source, target);
			GridCommunity result = gridCommunityRepository.save(gridCommunity);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(GridCommunity gridCommunity,Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		gridCommunity.setDelTag(1);
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withMatcher("gridName",ExampleMatcher.GenericPropertyMatchers.contains())
				.withIgnorePaths("manager")
				.withIgnorePaths("managerDept");
		// 创建实例
		//获取部门的ids
		List<String> ids = new ArrayList<>();
		if(gridCommunity.getManagerDept()!=null && !"".equals(gridCommunity.getManagerDept()))
		{
			ids = gridCommunityRepository.getDepIds(gridCommunity.getManagerDept());
			if(ids.size()==0)
			{
				ids.add("none");
			}
		}
		//获取人员的ids
		List<String> userIds = new ArrayList<>();
		if(gridCommunity.getManager()!=null && !"".equals(gridCommunity.getManager()))
		{
			userIds = gridCommunityRepository.getUserIds(gridCommunity.getManager());
			if(userIds.size()==0)
			{
				userIds.add("none");
			}
		}

		final List<String>idsTmp = ids;
		final List<String>userIdsTmp = userIds;

		Example<GridCommunity> example = Example.of(gridCommunity, matcher);
		Long total = gridCommunityRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		List<GridCommunity> list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() !=null ) {
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize()); // getPageNo默认从0开始
			list = gridCommunityRepository.findAll(example, pageable).getContent();
		} else {
			list = gridCommunityRepository.findAll(example);
		}
		Map<String, Object> result = new HashMap<>();

		if(ids.size() >0)
		{
			list = (List) list.stream().filter(a->idsTmp.contains(a.getManagerDept())).collect(Collectors.toList());
		}
		if(userIds.size()>0)
		{
			list = (List) list.stream().filter(a->userIdsTmp.contains(a.getManager())).collect(Collectors.toList());
		}
		result.put("data", list);
		result.put("pageNo", page.getPageNo());
		result.put("totalCount", total);
		if (page.getPageNo() != null && page.getPageSize() !=null ) {
			result.put("totalPage", total % page.getPageSize() == 0 ? total / page.getPageSize() : total / page.getPageSize() + 1);
		}
		
		return new BaseResponse<>(200, "success", result);
	}
	@Override
	public Object tree(String id) {
		// TODO Auto-generated method stub
		JSONArray result = recursion(id,new JSONArray());
		JSONArray children = result.getJSONObject(0).getJSONArray("children");
		if (children.size() > 0 && "根目录".equals(result.getJSONObject(0).getString("title"))) {
			result = children;
		}
		return new BaseResponse<>(200, "success", result);
	}

	@Override
	public Object getIdAndName() {
		List<Map<String,String>> list = gridCommunityRepository.getIdAndName();
		Map<String, Object> result = new HashMap<>();
		result.put("data", list);
		return new BaseResponse<>(200, "success", result);
	}

	@Override
	public Object getUserIdAndName() {
		List<Map<String,String>> list = gridCommunityRepository.getUserIdAndName();
		Map<String, Object> result = new HashMap<>();
		result.put("data", list);
		return new BaseResponse<>(200, "success", result);
	}

	@Override
	public Object checkGridName(String name,String id) {
		int count = gridCommunityRepository.checkGridName(name,id);
		if(count==0)
		{
			return new BaseResponse<>(200, "success");
		}else {
			return new BaseResponse<>(500, "网格名称重复");
		}

	}

	@Override
	public Object getTelephone(String id) {
		return new BaseResponse<>(200, "success", gridCommunityRepository.getTelephone(id));
	}

	public JSONArray recursion(String id, JSONArray jsonArray) {
		List<GridCommunity> list = getSubMenu(id);
		for (GridCommunity menu : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", menu.getId());
			jsonObject.put("key", menu.getId());
			jsonObject.put("title", menu.getGridName());
			jsonObject.put("children", recursion(menu.getId(), new JSONArray()));
			jsonObject.put("icon", menu.getIcon());
			jsonObject.put("manager", menu.getManager());
			jsonObject.put("managerDept", menu.getManagerDept());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	public List<GridCommunity> getSubMenu (String id) {
		GridCommunity menu1 = new GridCommunity();
		menu1.setDelTag(1);
		menu1.setPid(id);
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		Example<GridCommunity> example = Example.of(menu1, matcher);
		List<Sort.Order> orders = new ArrayList<>();
		orders.add(new Sort.Order(Sort.Direction.ASC,"showOrder"));
		Sort sort = Sort.by(orders);
		List<GridCommunity> list = gridCommunityRepository.findAll(example,sort);
		return list;
	}

}
