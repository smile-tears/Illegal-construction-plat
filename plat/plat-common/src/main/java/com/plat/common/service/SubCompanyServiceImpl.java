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

import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.common.dao.SubCompanyRepository;
import com.plat.common.entity.SubCompany;

@Service
public class SubCompanyServiceImpl implements SubCompanyService {

	@Autowired
	SubCompanyRepository subCompanyRepository;

	@Autowired
	DepartmentService departmentService;
	
	@Override
	public Object save(SubCompany subCompany) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", subCompanyRepository.save(subCompany));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			int size = subCompanyRepository.getChildrenSubCompanyId(id).size();
			if (size > 0) {
				return new BaseResponse<>(500, "删除分部中含下级分部，请先移除下级分部！");
			}
			int size2 = departmentService.getSubDepartmentById(id, id).size();
			if (size2 > 0) {
				return new BaseResponse<>(500, "删除分部中含下级部门，请先移除下级部门！");
			}
		}
		subCompanyRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(SubCompany target) {
		// TODO Auto-generated method stub
		Optional<SubCompany> source = subCompanyRepository.findById(target.getId());
		if (source.isPresent()) {
			List<SubCompany> list = subCompanyRepository.getChildrenSubCompanyId(target.getId());
			if (list.size() > 0 && (!source.get().getSupSubCompanyId().equals(target.getSupSubCompanyId()))) {
				return new BaseResponse<>(500, "含有下级分部，请先移除下级分部！");
			}
			int size2 = departmentService.getSubDepartmentById(target.getId(), target.getId()).size();
			if (size2 > 0 && (!source.get().getSupSubCompanyId().equals(target.getSupSubCompanyId()))) {
				return new BaseResponse<>(500, "含有下级部门，请先移除下级部门！");
			}
			SubCompany subCompany = (SubCompany) BeanProcessUtils.copy(source.get(), target);
			SubCompany result = subCompanyRepository.save(subCompany);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(SubCompany subCompany,Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		subCompany.setDelTag(1);
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		// 创建实例
		Example<SubCompany> example = Example.of(subCompany, matcher);
		Long total = subCompanyRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Object list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() !=null ) {
			List<Sort.Order> orders = new ArrayList<>();
			orders.add(new Sort.Order(Sort.Direction.ASC,"showOrder"));
			Sort sort = Sort.by(orders);
			pageable=PageRequest.of(page.getPageNo() - 1, page.getPageSize(), sort);
			list = subCompanyRepository.findAll(example, pageable).getContent();
		} else {
			list = subCompanyRepository.findAll(example);
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
	public List<SubCompany> getChildrenSubCompanyId(String id) {
		// TODO Auto-generated method stub
		return subCompanyRepository.getChildrenSubCompanyId(id);
	}

}
