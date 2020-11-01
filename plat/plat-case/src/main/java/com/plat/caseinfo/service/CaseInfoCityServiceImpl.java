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
import com.plat.caseinfo.entity.CaseInfoFileCity;
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
		// 增加案件所属分部，便于统计
		String managerDept = caseInfoCity.getManagerDept();
		if (!StringUtils.isEmpty(managerDept) ) {
			Department department = departmentRepository.getOne(managerDept);
			if (department != null) { // 责任机构是部门时
				caseInfoCity.setSubCompany(department.getSubCompanyId());
			} else { // 责任机构是分部
				caseInfoCity.setSubCompany(managerDept);
			}
		}
		int status = caseInfoCity.getStatus();
		String reportTime = caseInfoCity.getReportTime();
		if ((status == 0 || status == 1 ) && StringUtils.isEmpty(reportTime)) {
			caseInfoCity.setReportTime(TimeUtil.getNowTime());
		}
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
			String managerDept = caseInfoCity.getManagerDept();
			if (!StringUtils.isEmpty(managerDept) ) {
				String subCompanyId = departmentRepository.getOne(managerDept).getSubCompanyId();
				caseInfoCity.setSubCompany(subCompanyId);
			}
			
			int status = caseInfoCity.getStatus();
			String reportTime = caseInfoCity.getReportTime();
			if ((status == 0 || status == 1 ) && StringUtils.isEmpty(reportTime)) {
				caseInfoCity.setReportTime(TimeUtil.getNowTime());
			}
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
		Integer status = caseInfoCity.getStatus();
		String operator = caseInfoCity.getOperator();
		Integer pageNo = page.getPageNo();
		Integer pageSize = page.getPageSize();
		// TODO Auto-generated method stub
		Integer start = (pageNo - 1 ) * pageSize;
		Integer offset = pageSize;
		String countSql = " select t1.status,t1.id,caseNo,concat(casesource1,'-',casesource2) as casesource, \r\n" + 
				" concat(case when t4.typeName is null then '' else concat(t4.typeName,'-') end "
				+ ",case when t3.typeName is null then '' else concat(t3.typeName,'-') end"
				+ ",case when t2.typeName is null then '' else t2.typeName end) as caseType, \r\n" + 
				" concat(case when t7.gridName is null then '' else concat(t7.gridName,'-') end"
				+ ",case when t6.gridName is null then '' else concat(t6.gridName,'-') end"
				+ ",case when t5.gridName is null then '' else t5.gridName end) as gridName, \r\n" + 
				" t8.departmentName as managerDept,t9.name, \r\n" + 
				" concat(case when t12.sampleName is null then '' else concat(t12.sampleName,'-') end "+
				" ,case when t11.sampleName is null then '' else concat(t11.sampleName,'-') end "
				+ ",case when t10.sampleName is null then '' else t10.sampleName end ) as sampleName, \r\n" + 
				" t1.limittimes,casecount,caseDesc,contract, \r\n" + 
				" reporTime, \r\n" + 
				" importantCase,repeatCase,locationDesc,lng,lat,\r\n" + 
				" (select id from caseinfo_requestlog_city where caseid=t1.id and status="+status+" ORDER BY createTime desc LIMIT 0,1) as requestLogId1,\r\n" + 
				//" (select receivetime from caseinfo_requestlog_city where caseid=t1.id and nodeid="+status+" and operator='"+userid+"' ORDER BY createTime desc LIMIT 0,1) as receivetime, \r\n" + 
				" (select id from caseinfo_requestlog_city where caseid=t1.id and nodeid="+status+" and operator='"+operator+"' ORDER BY createTime desc LIMIT 0,1) as requestLogId2, \r\n" + 
				" t13.name reportor, t1.reportor as reportorId," +
				" case when timestampdiff(SECOND,NOW(),enddate)/timestampdiff(SECOND,setupdate,enddate) >= (1/3) then '0'\r\n" + 
				" when timestampdiff(SECOND,NOW(),enddate)/timestampdiff(SECOND,setupdate,enddate) >= 0 then '1'\r\n" + 
				" when timestampdiff(SECOND,NOW(),enddate)/timestampdiff(SECOND,setupdate,enddate) < 0 then '2'\r\n" + 
				" end as state,t1.enddate " +
				" from caseinfo_city t1  \r\n" + 
				" LEFT JOIN questiontype t2 on t1.casetype3=t2.id and t2.pid<>'0' \r\n" + 
				" LEFT JOIN questiontype t3 on t2.pid=t3.id and t3.pid<>'0'\r\n" + 
				" LEFT JOIN questiontype t4 on t3.pid=t4.id and t4.pid<>'0' \r\n" + 
				" LEFT JOIN gridcommunity t5 on t1.gridCommunityId=t5.id and t5.pid<>'0' \r\n" + 
				" LEFT JOIN gridcommunity t6 on t5.pid=t6.id and t6.pid<>'0' \r\n" + 
				" LEFT JOIN gridcommunity t7 on t6.pid=t7.id and t7.pid<>'0' \r\n" + 
				" LEFT JOIN department t8 on t1.managerDept=t8.id \r\n" + 
				" LEFT JOIN user t9 on t1.manager=t9.id \r\n" + 
				" LEFT JOIN sample t10 on t1.sample3=t10.id and t10.supSampleId<>'0'\r\n" + 
				" LEFT JOIN sample t11 on t10.supSampleId=t11.id and t11.supSampleId<>'0'\r\n" + 
				" LEFT JOIN sample t12 on t11.supSampleId=t12.id and t12.supSampleId<>'0' \r\n" + 
				" LEFT JOIN user t13 on t1.reportor=t13.id \r\n" + 
				" where t1.deltag=1 ";
		
		if (!StringUtils.isEmpty(status)) {
			countSql += " and t1.status='"+status+"'";
		} 
		if ("screen".equals(source)) {
			countSql += " and t1.status>=2 && t1.status not in (3,5,10)";
		}
		if (!StringUtils.isEmpty(caseInfoCity.getCaseNo())) {
			countSql += " and t1.caseNo like '%"+caseInfoCity.getCaseNo()+"%'";
		}
		if (!StringUtils.isEmpty(caseInfoCity.getCaseDesc())) {
			countSql += " and t1.caseDesc like '%"+caseInfoCity.getCaseDesc()+"%'";
		}
		if (!StringUtils.isEmpty(operator)) {
			countSql += " and exists (select id from caseinfo_requestlog_city where caseid=t1.id and nodeid="+status+" and operator='"+operator+"') ";
		}
		countSql += " ORDER BY t1.operatetime desc,setupdate desc, reporTime desc";
		String dataSql = countSql;
		if (!StringUtils.isEmpty(pageNo) && !StringUtils.isEmpty(pageSize)) {
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

	/**
	 *获取今日案件上报数、今日处置数、今日分派数、及各个区局案件相关数据
	 */
	@Override
	public Object getCaseReportData() {
		// TODO Auto-generated method stub
		String currentDate = TimeUtil.getNowTime("yyyy-MM-dd");
		String[] types = {"上报","结案","处置"};
		String[] fields = {"reporTime","closedate","dealdate"};

		JSONObject data = new JSONObject();
		for (int i = 0 ; i < fields.length; i++) {
			String sql = "SELECT id from caseinfo_city where delTag=1 and "+fields[i]+" like '"+currentDate+"%'";
			int count = entityManager.createNativeQuery(sql).getResultList().size();
			data.put(types[i], count);
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0 ; i < fields.length; i++) {
			sb.append("{\"name\": \""+types[i]+"\"");
			String sql = "SELECT t2.subcompanyname,count(1) as count from caseinfo_area t1 " + 
					"LEFT JOIN subcompany t2 on t1.subCompany=t2.id " + 
					"where t1.delTag=1 " + 
					"and "+fields[i]+" like '"+currentDate+"%' group by subCompany ORDER BY t2.showOrder";
			List<Map<String, Object>> resultList = entityManager.createNativeQuery(sql)
					.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
			for (Map<String, Object> map : resultList) {
				sb.append(",\""+map.get("subcompanyname")+"\": \""+map.get("count")+"\"");
			}
			sb.append("},");
		}
		String json = "";
		if (sb.length() > 1) json = sb.substring(0, sb.length()-1);
		json = json + "]";
		data.put("jsonStr", json);
		return new BaseResponse<>(200, "success", data);
	}

	
}
