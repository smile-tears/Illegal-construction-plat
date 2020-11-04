package com.plat.caseinfo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.plat.common.dao.DepartmentRepository;
import com.plat.common.dao.UserRepository;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Department;
import com.plat.common.entity.Page;
import com.plat.common.entity.User;
import com.plat.common.service.UserService;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.common.utils.TimeUtil;

import com.plat.caseinfo.entity.CaseInfoCity;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.plat.caseinfo.dao.CaseInfoCityRepository;
@Service
public class CaseInfoCityServiceImpl implements CaseInfoCityService {

	@Autowired
	CaseInfoCityRepository caseInfoCityRepository;

	@Autowired
	UserService userService;
	
	@PersistenceContext 
    EntityManager entityManager;
	
	@Autowired
	//UserRepository userRepository;
	DepartmentRepository departmentRepository;

	@Override
	public Object save(CaseInfoCity caseInfoCity) {
		// TODO Auto-generated method stub

		return new BaseResponse<>(200, "success",caseInfoCityRepository.save(caseInfoCity));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		caseInfoCityRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(CaseInfoCity target) {
		// TODO Auto-generated method stub
		Optional<CaseInfoCity> source = caseInfoCityRepository.findById(target.getId());
		if (source.isPresent()) {
			CaseInfoCity caseInfoCity = (CaseInfoCity) BeanProcessUtils.copy(source.get(), target);
			CaseInfoCity result = caseInfoCityRepository.save(caseInfoCity);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(CaseInfoCity caseInfoCity,Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		caseInfoCity.setDelTag(1);
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withMatcher("locationDesc",ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("caseDesc",ExampleMatcher.GenericPropertyMatchers.contains())
				.withIgnorePaths("limittimes");
		// 创建实例
		Example<CaseInfoCity> example = Example.of(caseInfoCity, matcher);
		Long total = caseInfoCityRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Integer totalPage = null;
		Object list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() !=null ) {
			List<Sort.Order> orders = new ArrayList<>();
			orders.add(new Sort.Order(Sort.Direction.DESC,"reportDate"));
			orders.add(new Sort.Order(Sort.Direction.DESC,"reportTime"));
			Sort sort = Sort.by(orders);
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize(),sort); // pageIndex默认从0开始
			list = caseInfoCityRepository.findAll(example, pageable).getContent();
			totalPage = (int) (total % page.getPageSize() == 0 ? total / page.getPageSize() : total / page.getPageSize() + 1);
		} else {
			list = caseInfoCityRepository.findAll(example);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("data", list);
		result.put("pageNo", page.getPageNo()); 
		result.put("pageSize", page.getPageSize());
		result.put("totalCount", total);
		result.put("totalPage", totalPage);
		return new BaseResponse<>(200, "success", result);
	}
	
	@Override
	@Transactional(readOnly = true) // 解决 com.sun.proxy.$Proxy306 cannot be cast to org.hibernate.query.internal.NativeQueryImpl
	public Object find2(String source,CaseInfoCity caseInfoCity, Page page,HttpServletRequest request ) {
//		String operator = "";
//		if (request != null) operator = userService.getUserByToken(request).getId();
		Integer pageNo = page.getPageNo();
		Integer pageSize = page.getPageSize();
		// TODO Auto-generated method stub
		
		String countSql = " SELECT t1.*,t2.companyName,t3.gridName,t4.name " + 
				"from caseinfo_city t1 " + 
				"LEFT JOIN companymanage t2 on t1.companyid=t2.id " + 
				"LEFT JOIN gridcommunity t3 on t1.gridId=t3.id " + 
				"LEFT JOIN user t4 on t1.reportor=t4.id ";

		countSql += " ORDER BY t1.operatetime desc ";
		String dataSql = countSql;
		if (!StringUtils.isEmpty(pageNo) && !StringUtils.isEmpty(pageSize)) {
			Integer start = (pageNo - 1 ) * pageSize;
			Integer offset = pageSize;
			dataSql += " limit " + start + "," + offset;
		}
		// System.out.println("=========="+dataSql);
		int total = entityManager.createNativeQuery(countSql).getResultList().size();
		List<Map<String, Object>> resultList = entityManager.createNativeQuery(dataSql)
				.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
		Map<String, Object> result = new HashMap<>();
		result.put("data", resultList);
		result.put("pageNo", pageNo); 
		result.put("pageSize", pageSize);
		result.put("totalCount", total);
		if (pageNo == null || pageSize ==null) {
			result.put("totalPage",1);
		} else {
			result.put("totalPage", total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
		}
		return new BaseResponse<>(200, "success", result);
		// return null;
	}

	
}
