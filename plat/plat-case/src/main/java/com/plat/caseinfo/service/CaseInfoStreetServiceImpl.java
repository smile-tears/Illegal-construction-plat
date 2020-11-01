package com.plat.caseinfo.service;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.plat.common.dao.UserRepository;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.common.utils.TimeUtil;
import com.plat.caseinfo.dao.CaseInfoStreetRepository;
import com.plat.caseinfo.entity.CaseInfoStreet;
import com.plat.caseinfo.entity.CaseInfoFileStreet;

@Service
public class CaseInfoStreetServiceImpl implements CaseInfoStreetService {

	@Autowired
	CaseInfoStreetRepository caseInfoStreetRepository;
	
	@PersistenceContext 
    EntityManager entityManager;

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Object save(CaseInfoStreet caseInfoStreet) {
		// TODO Auto-generated method stub
		// 增加上报人所属分部，便于统计
		String reportor = caseInfoStreet.getReportor();
		if (reportor != null) {
			String subCompanyId = userRepository.getOne(reportor).getSubCompanyId();
			caseInfoStreet.setReportorSubCompany(subCompanyId);
		}
		return new BaseResponse<>(200, "success",caseInfoStreetRepository.save(caseInfoStreet));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		caseInfoStreetRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(CaseInfoStreet target) {
		// TODO Auto-generated method stub
		Optional<CaseInfoStreet> source = caseInfoStreetRepository.findById(target.getId());
		if (source.isPresent()) {
			CaseInfoStreet caseInfoStreet = (CaseInfoStreet) BeanProcessUtils.copy(source.get(), target);
			String reportor = caseInfoStreet.getReportor();
			if (reportor != null) {
				String subCompanyId = userRepository.getOne(reportor).getSubCompanyId();
				caseInfoStreet.setReportorSubCompany(subCompanyId);
			}
			CaseInfoStreet result = caseInfoStreetRepository.save(caseInfoStreet);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(CaseInfoStreet caseInfoStreet,Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		caseInfoStreet.setDelTag(1);
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withMatcher("locationDesc",ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("caseDesc",ExampleMatcher.GenericPropertyMatchers.contains());
		// 创建实例
		Example<CaseInfoStreet> example = Example.of(caseInfoStreet, matcher);
		Long total = caseInfoStreetRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Integer totalPage = null;
		Object list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() !=null ) {
			List<Sort.Order> orders = new ArrayList<>();
			orders.add(new Sort.Order(Sort.Direction.DESC,"reportTime"));
			Sort sort = Sort.by(orders);
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize(),sort); // pageIndex默认从0开始
			list = caseInfoStreetRepository.findAll(example, pageable).getContent();
			totalPage = (int) (total % page.getPageSize() == 0 ? total / page.getPageSize() : total / page.getPageSize() + 1);
		} else {
			list = caseInfoStreetRepository.findAll(example);
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
	public Object find2(int status, Page page,String operator ) {
		Integer pageNo = page.getPageNo();
		Integer pageSize = page.getPageSize();
		// TODO Auto-generated method stub
		Integer start = (pageNo - 1 ) * pageSize;
		Integer offset = pageSize;
		String countSql = " select t1.status,t1.id,caseNo,concat(casesource1,'-',casesource2) as casesource, \r\n" + 
				" concat(ifnull(t4.typeName,''),'-',ifnull(t3.typeName,''),'-',ifnull(t2.typeName,'')) as caseType, \r\n" + 
				" concat(ifnull(t7.gridName,''),'-',ifnull(t6.gridName,''),'-',ifnull(t5.gridName,'')) as gridName, \r\n" + 
				" t8.departmentName as managerDept,t9.name, \r\n" + 
				" concat(ifnull(t12.sampleName,''),'-',ifnull(t11.sampleName,''),'-',ifnull(t10.sampleName,'')) as sampleName, \r\n" + 
				" t1.limittimes,casecount,caseDesc,reportor,contract, \r\n" + 
				" reporTime, \r\n" + 
				" importantCase,repeatCase,locationDesc,lng,lat,\r\n" + 
				" (select id from caseinfo_requestlog_street where caseid=t1.id and status="+status+" ORDER BY createTime desc LIMIT 0,1) as requestLogId1,\r\n" + 
				" (select id from caseinfo_requestlog_street where caseid=t1.id and status="+status+" and operator='"+operator+"' ORDER BY createTime desc LIMIT 0,1) as requestLogId2 \r\n" + 
				" from caseinfo_street t1  \r\n" + 
				" LEFT JOIN questiontype t2 on t1.casetype3=t2.id \r\n" + 
				" LEFT JOIN questiontype t3 on t2.pid=t3.id \r\n" + 
				" LEFT JOIN questiontype t4 on t3.pid=t4.id \r\n" + 
				" LEFT JOIN gridcommunity t5 on t1.gridCommunityId=t5.id \r\n" + 
				" LEFT JOIN gridcommunity t6 on t5.pid=t6.id \r\n" + 
				" LEFT JOIN gridcommunity t7 on t6.pid=t7.id \r\n" + 
				" LEFT JOIN department t8 on t1.managerDept=t8.id \r\n" + 
				" LEFT JOIN user t9 on t1.manager=t9.id \r\n" + 
				" LEFT JOIN sample t10 on t1.sample3=t10.id \r\n" + 
				" LEFT JOIN sample t11 on t10.supSampleId=t11.id \r\n" + 
				" LEFT JOIN sample t12 on t11.supSampleId=t12.id \r\n" + 
				" where t1.deltag=1 ";
		if (!StringUtils.isEmpty(status)) {
			countSql += " and t1.status='"+status+"'";
		}
		if (!StringUtils.isEmpty(operator)) {
			countSql += " t13.operator='"+operator+"'";
		}
		String dataSql = countSql;
		if (!StringUtils.isEmpty(pageNo) && !StringUtils.isEmpty(pageSize)) {
			dataSql += " limit " + start + "," + offset;
		}
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
