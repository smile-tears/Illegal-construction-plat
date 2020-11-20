package com.plat.sysconfig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.sysconfig.dao.CompanyManageRepository;
import com.plat.sysconfig.entity.CompanyManage;

@Service
public class CompanyManageServiceImpl implements CompanyManageService {

	@Autowired
	CompanyManageRepository companyManageRepository;
	
	@PersistenceContext 
    EntityManager entityManager;

	@Override
	public Object save(CompanyManage companyManage) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", companyManageRepository.save(companyManage));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		companyManageRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(CompanyManage target) {
		// TODO Auto-generated method stub
		Optional<CompanyManage> source = companyManageRepository.findById(target.getId());
		if (source.isPresent()) {
			CompanyManage companyManage = (CompanyManage) BeanProcessUtils.copy(source.get(), target);
			CompanyManage result = companyManageRepository.save(companyManage);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(CompanyManage companyManage, Page page) {
		Integer pageNo = page.getPageNo();
		Integer pageSize = page.getPageSize();
		String countSql = " SELECT t1.*,t3.name,t3.telephone,t2.gridname " + 
				" from CompanyManage t1  " + 
				" LEFT JOIN gridcommunity t2 on t1.grid=t2.id " + 
				" LEFT JOIN user t3 on t1.safetyOffice=t3.id " + 
				" where t1.delTag=1   ";
		if (!StringUtils.isEmpty(companyManage.getCompanyName())) {
			countSql += " and t1.CompanyName like '%"+companyManage.getCompanyName()+"%'";
		}
		if (!StringUtils.isEmpty(companyManage.getSafetyOffice())) {
			countSql += " and t1.SafetyOffice = '"+companyManage.getSafetyOffice()+"'";
		}
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
		result.put("pageNo", page.getPageNo());
		result.put("totalCount", total);
		if (page.getPageNo() != null && page.getPageSize() !=null ) {
			result.put("totalPage", total % page.getPageSize() == 0 ? total / page.getPageSize() : total / page.getPageSize() + 1);
		}
		
		return new BaseResponse<>(200, "success", result);

		// 查询条件设置默认值
//		companyManage.setDelTag(1);
//		// 创建匹配器，需要查询条件请修改此处代码
//		ExampleMatcher matcher = ExampleMatcher.matchingAll().withMatcher("companyName",
//				ExampleMatcher.GenericPropertyMatchers.contains());
//		// 创建实例
//		Example<CompanyManage> example = Example.of(companyManage, matcher);
//		Long total = companyManageRepository.count(example);
//		// 分页构造
//		Pageable pageable = null;
//		Integer totalPage = null;
//		Object list = new ArrayList<>();
//		if (page.getPageNo() != null && page.getPageSize() != null) {
//			List<Sort.Order> orders = new ArrayList<>();
//			orders.add(new Sort.Order(Sort.Direction.ASC, "showOrder"));
//			Sort sort = Sort.by(orders);
//			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize(), sort); // pageIndex默认从0开始
//			list = companyManageRepository.findAll(example, pageable).getContent();
//			totalPage = (int) (total % page.getPageSize() == 0 ? total / page.getPageSize()
//					: total / page.getPageSize() + 1);
//		} else {
//			list = companyManageRepository.findAll(example);
//		}
//		Map<String, Object> result = new HashMap<>();
//		result.put("data", list);
//		result.put("pageNo", page.getPageNo());
//		result.put("pageSize", page.getPageSize());
//		result.put("totalCount", total);
//		if (page.getPageNo() == null || page.getPageSize() == null) {
//			result.put("totalPage", 1);
//		} else {
//			result.put("totalPage", totalPage);
//		}
//		return new BaseResponse<>(200, "success", result);
	}
}
