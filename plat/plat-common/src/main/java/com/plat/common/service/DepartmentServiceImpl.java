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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.common.dao.DepartmentRepository;
import com.plat.common.entity.Department;
import com.plat.common.entity.User;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	UserService userService;

	@Override
	public Object save(Department department) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", departmentRepository.save(department));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			int size = departmentRepository.getSubDepartmentById(id).size();
			if (size > 0) {
				return new BaseResponse<>(500, "删除部门中含下级部门，请先移除下级部门！");
			}
			User user = new User();
			user.setDepartmentId(id);
			JSONObject object = (JSONObject)JSONObject.toJSON(userService.find(user, new Page()));
			JSONArray data = object.getJSONObject("result").getJSONArray("data");
			if (data.size() >0 ) {
				return new BaseResponse<>(500, "删除部门中含有人员，请先移除人员！");
			}
		}
		departmentRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(Department target) {
		// TODO Auto-generated method stub
		Optional<Department> source = departmentRepository.findById(target.getId());
		if (source.isPresent()) {
            //查询是否更新的节点下面是否还有子节点
			List<Department> deptlist = departmentRepository.getSubDepartmentById(target.getId());
			if(deptlist.size()>0&& (!target.getSupDeptId().equals(source.get().getSupDeptId()))){
				return new BaseResponse<>(500,"含有下级部门，请先移除下级部门！");
			}
			User user = new User();
			user.setDepartmentId(target.getId());
			JSONObject object = (JSONObject)JSONObject.toJSON(userService.find(user, new Page()));
			JSONArray data = object.getJSONObject("result").getJSONArray("data");
			if (data.size() >0 ) {
				return new BaseResponse<>(500, "部门中含有人员，请先移除人员！");
			}
			Department department = (Department) BeanProcessUtils.copy(source.get(), target);
			Department result = departmentRepository.save(department);
			return new BaseResponse<>(200, "success", result);
		

		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(Department department,Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		department.setDelTag(1);
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		// 创建实例
		Example<Department> example = Example.of(department, matcher);
		Long total = departmentRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Object list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() !=null ) {
			List<Sort.Order> orders = new ArrayList<>();
			orders.add(new Sort.Order(Sort.Direction.ASC,"showOrder"));
			Sort sort = Sort.by(orders);
			pageable=PageRequest.of(page.getPageNo() - 1, page.getPageSize(), sort);
			list = departmentRepository.findAll(example, pageable).getContent();
		} else {
			list = departmentRepository.findAll(example);
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

	@Override
	public List<Department> getSubDepartmentById(String id,String subCompanyId) {
		// TODO Auto-generated method stub
		if (!StringUtils.isEmpty(subCompanyId)) {
			return departmentRepository.getSubDepartmentById(id,subCompanyId);
		} else {
			return  departmentRepository.getSubDepartmentById(id);
		}

	}

}
