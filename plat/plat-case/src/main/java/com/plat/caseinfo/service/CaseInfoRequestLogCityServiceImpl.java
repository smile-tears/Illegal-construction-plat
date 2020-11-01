package com.plat.caseinfo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.plat.common.dao.UserRepository;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.entity.User;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.common.utils.JwtUtils;
import com.plat.common.utils.TimeUtil;
import com.plat.sysconfig.dao.SysGlobalConfigRepository;
import com.plat.sysconfig.entity.SysGlobalConfig;
import com.plat.sysconfig.util.IntervalTimeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.plat.caseinfo.dao.CaseInfoAreaRepository;
import com.plat.caseinfo.dao.CaseInfoCityRepository;
import com.plat.caseinfo.dao.CaseInfoFileCityRepository;
import com.plat.caseinfo.dao.CaseInfoRequestLogCityRepository;
import com.plat.caseinfo.entity.CaseInfoArea;
import com.plat.caseinfo.entity.CaseInfoCity;
import com.plat.caseinfo.entity.CaseInfoFileCity;
import com.plat.caseinfo.entity.CaseInfoRequestLogArea;
import com.plat.caseinfo.entity.CaseInfoRequestLogCity;
import com.plat.caseinfo.web.WebSocketConfig;
import com.plat.caseinfo.web.WebSocketServerConfig;

@Service
public class CaseInfoRequestLogCityServiceImpl implements CaseInfoRequestLogCityService {

	@Autowired
	CaseInfoRequestLogCityRepository caseInfoRequestLogCityRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CaseInfoCityRepository caseInfoCityRepository;
	
	@Autowired
	CaseInfoAreaRepository caseInfoAreaRepository;

	@Autowired
	CaseInfoCityService caseInfoCityService;
	
	@Autowired
	CaseInfoFileCityRepository caseInfoFileCityRepository;
	
	@Autowired 
	WebSocketServerConfig webSocketServerConfig;
	
	@Autowired
	SysGlobalConfigRepository sysGlobalConfigRepository;
	
	
	@PersistenceContext 
    EntityManager entityManager;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Object save(CaseInfoRequestLogCity caseInfoRequestLogCity,HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = request.getHeader("token");
		String username = JwtUtils.getClaimFiled(token, "username");
		User user = userRepository.getUserByUsername(username);
		int status = caseInfoRequestLogCity.getStatus();//当前状态
		String result = caseInfoRequestLogCity.getResult();//处理结果
		String caseId = caseInfoRequestLogCity.getCaseid();//案件id
		//更新案件表
		CaseInfoCity caseInfoCity = caseInfoCityRepository.getOne(caseId);
		String casesource1 = caseInfoCity.getCasesource1();
		String laststatus = caseInfoCity.getLaststatus();
		caseInfoCity.setStatus(result.contains("laststatus") ? Integer.parseInt(laststatus) : Integer.parseInt(result));
		caseInfoCity.setLaststatus(status+"");
		caseInfoCity.setOperator(user.getId());
		caseInfoCity.setOperatetime(TimeUtil.getNowTime());
		
		if (result.equals("1")) { //上报提交
			
		} else if (result.equals("2")) { //立案
			//生成案件编号
			String caseNo = caseInfoCityRepository.getCaseFlowCode(casesource1.equals("内部") ? "N" : "W", 8);
			String currentTime = TimeUtil.getNowTime();
			if (caseInfoCity.getCaseNo() == null) {
				caseInfoCity.setCaseNo(caseNo);
				caseInfoCity.setSetupdate(currentTime);
			}
			//计算案件截止日期
			try {
				double limittime = caseInfoCity.getLimittimes();
				int type = 1;
				List<SysGlobalConfig> sysGlobalConfigs = sysGlobalConfigRepository.findAll();
				if (sysGlobalConfigs.size() >0 ) {
					type = sysGlobalConfigs.get(0).getCalculateTypeOfWorkTime();
				}
				String enddate = IntervalTimeUtil.getEndDate(currentTime,limittime , type);
				caseInfoCity.setEnddate(enddate);
				// 流转表也要增加截止日期
				caseInfoRequestLogCity.setEnddate(enddate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (result.equals("3")) { //分派
			caseInfoCity.setAssigndate(TimeUtil.getNowTime());
			
		} else if (result.equals("4")) { //处置反馈件--指挥中心
			caseInfoCity.setDealdate(TimeUtil.getNowTime());
			
		} else if (result.equals("5")) { //案件核查
			
		} else if (result.equals("6")) { //核查反馈件
			caseInfoCity.setVerifydate(TimeUtil.getNowTime());
			
		} else if (result.equals("7")) { //申请回退
			
		} else if (result.equals("8")) { //申请延期 
			//把申请延期时间更新到主表方便后面替换 enddate
			caseInfoCity.setEnddate2(caseInfoRequestLogCity.getEnddate());
			
		} else if (result.equals("10")) { //结案
			caseInfoCity.setClosedate(TimeUtil.getNowTime());
			
		} else if (result.equals("laststatus1") ) {
			// 同意延期后，更新案件截至日期
			caseInfoCity.setEnddate(caseInfoCity.getEnddate2());
		}
		caseInfoCityRepository.save(caseInfoCity);
		//更新流转表
		caseInfoRequestLogCity.setNodeid(status+"");//当前节点
		caseInfoRequestLogCity.setDestnodeid(result);//下一节点
		caseInfoRequestLogCity.setOperator(user.getId());//操作人
		caseInfoRequestLogCity.setOperatetime(TimeUtil.getNowTime());//操作时间
		List<CaseInfoFileCity> files =  caseInfoRequestLogCity.getFiles();
		if (files != null) {
			for (CaseInfoFileCity file : files) {
				file.setRequestLog(caseInfoRequestLogCity);
			}
		}

		if (caseInfoRequestLogCity.getId() != null) {
			Optional<CaseInfoRequestLogCity> source = caseInfoRequestLogCityRepository.findById(caseInfoRequestLogCity.getId());
			if (source.isPresent()) {
				caseInfoRequestLogCity = (CaseInfoRequestLogCity) BeanProcessUtils.copy(source.get(), caseInfoRequestLogCity);
				
			}
		}
		caseInfoRequestLogCity = caseInfoRequestLogCityRepository.save(caseInfoRequestLogCity);
		if (result.equals("3")) { //分派
			// 主办人、协办人
			String sponsor = caseInfoRequestLogCity.getSponsor();
			if (sponsor != null) {
				User sponsorTemp = userRepository.getOne(sponsor);
				CaseInfoRequestLogCity temp = new CaseInfoRequestLogCity();
				temp.setCaseid(caseId);
				temp.setNodeid(result+"");
				// temp.setDestnodeid(result);
				temp.setOperator(sponsor);//操作人
				if (sponsorTemp != null) { //人员
					temp.setOperatorType(0);
					caseInfoRequestLogCityRepository.save(temp);
				} else { //分部
					temp.setOperatorType(3); //下派
					caseInfoRequestLogCityRepository.save(temp);
					// 往区表添加案件记录
					CaseInfoArea caseInfoArea = new CaseInfoArea();
					BeanProcessUtils.copy(caseInfoCity,caseInfoArea);
					caseInfoArea.setId(null);
					caseInfoArea.setStatus(9);
					caseInfoArea.setCityCaseId(caseInfoCity.getId());
					CaseInfoArea resultArea = caseInfoAreaRepository.save(caseInfoArea);
					
				}
				
			}
			String coOrganizer = caseInfoRequestLogCity.getCoOrganizer();
			if (coOrganizer != null) {
				User coOrganizerTemp = userRepository.getOne(coOrganizer);
				CaseInfoRequestLogCity temp = new CaseInfoRequestLogCity();
				temp.setCaseid(caseId);
				temp.setNodeid(result+"");
				// temp.setDestnodeid(result);
				temp.setOperator(coOrganizer);//操作人
				if (coOrganizerTemp != null) { //人员
					temp.setOperatorType(1);
					caseInfoRequestLogCityRepository.save(temp);
				} else { //分部
					temp.setOperatorType(3); //下派
					caseInfoRequestLogCityRepository.save(temp);
					// 往区表添加案件记录
					CaseInfoArea caseInfoArea = new CaseInfoArea();
					BeanProcessUtils.copy(caseInfoCity,caseInfoArea);
					caseInfoArea.setId(null);
					caseInfoArea.setStatus(9);
					caseInfoArea.setCityCaseId(caseInfoCity.getId());
					CaseInfoArea resultArea = caseInfoAreaRepository.save(caseInfoArea);
					
				}
			}
			
		} else if (result.equals("5")) { //案件核查
			//核查人
			String verifier = caseInfoRequestLogCity.getVerifier();
			if (verifier != null) {
				User verifierTemp = userRepository.getOne(verifier);
				CaseInfoRequestLogCity temp = new CaseInfoRequestLogCity();
				temp.setCaseid(caseId);
				temp.setNodeid(result+"");
				// temp.setDestnodeid(result);
				temp.setOperator(verifier);//操作人
				if (verifierTemp != null) { //人员
					temp.setOperatorType(2);
					caseInfoRequestLogCityRepository.save(temp);
				} else { //分部
					temp.setOperatorType(3); //下派
					caseInfoRequestLogCityRepository.save(temp);
					// 往区表添加案件记录
					CaseInfoArea caseInfoArea = new CaseInfoArea();
					BeanProcessUtils.copy(caseInfoCity,caseInfoArea);
					caseInfoArea.setId(null);
					caseInfoArea.setStatus(9);
					caseInfoArea.setCityCaseId(caseInfoCity.getId());
					CaseInfoArea resultArea = caseInfoAreaRepository.save(caseInfoArea);
					
				}
			}
		}
		// 从立案开始，数据变更刷新前端指挥中心大屏列表
		if (status >= 1) { 
			JSONObject data = new JSONObject();
			data.put("nodeid", status);
			data.put("destnodeid", result);
			webSocketServerConfig.sendAllInfo(data.toJSONString());
			
		}
		if (status>=0) {
			Object caseList = caseInfoCityService.find2("screen",new CaseInfoCity(), new Page(1, 4), request);
			Object reportData = caseInfoCityService.getCaseReportData();
			JSONObject object = new JSONObject();
			object.put("caseList", caseList);
			object.put("reportData", reportData);
			webSocketServerConfig.sendAllInfo2(JSON.toJSONString(object));
		}
		
		return new BaseResponse<>(200, "success");
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		caseInfoRequestLogCityRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(CaseInfoRequestLogCity target) {
		// TODO Auto-generated method stub
		Optional<CaseInfoRequestLogCity> source = caseInfoRequestLogCityRepository.findById(target.getId());
		if (source.isPresent()) {
			CaseInfoRequestLogCity caseInfoRequestLogCity = (CaseInfoRequestLogCity) BeanProcessUtils.copy(source.get(), target);
			List<CaseInfoFileCity> files =  caseInfoRequestLogCity.getFiles();
			for (CaseInfoFileCity file : files) {
				file.setRequestLog(caseInfoRequestLogCity);
			}
			CaseInfoRequestLogCity result = caseInfoRequestLogCityRepository.save(caseInfoRequestLogCity);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(CaseInfoRequestLogCity caseInfoRequestLogCity,Page page) {
		Integer pageNo = page.getPageNo();
		pageNo  = pageNo == null ? 1: pageNo;
		Integer pageSize = page.getPageSize();
		pageSize = pageSize == null ? 10 : pageSize;
		// TODO Auto-generated method stub
		Integer start = (pageNo - 1 ) * pageSize;
		Integer offset = pageSize;
		String countSql = " select t1.id,(select name from user where id=t1.operator) operator ,status,idea,result,enddate,operatetime,\r\n" + 
				"(select name from user where id=t1.sponsor) sponsor,\r\n" + 
				"(select name from user where id=t1.coOrganizer) coOrganizer, \r\n" + 
				"(select name from user where id=t1.verifier) verifier\r\n" + 
				"from caseinfo_requestlog_city t1\r\n" + 
				"where 1=1  ";
		if (caseInfoRequestLogCity.getCaseid() != null) {
			countSql += " and caseid='"+caseInfoRequestLogCity.getCaseid()+"'";
		} 
		if (caseInfoRequestLogCity.getId() != null) {
			countSql += " and id='"+caseInfoRequestLogCity.getId()+"'";
		}
		if (caseInfoRequestLogCity.getStatus() != null) {
			countSql += " and status=" + caseInfoRequestLogCity.getStatus();
		}
		countSql += " order by createTime";
		String dataSql = countSql;
		if (!StringUtils.isEmpty(pageNo) && !StringUtils.isEmpty(pageSize)) {
			dataSql += " limit " + start + "," + offset;
		}
		// System.out.println("=========="+dataSql);
		int total = entityManager.createNativeQuery(countSql).getResultList().size();
		List<Map<String, Object>> resultList = entityManager.createNativeQuery(dataSql)
				.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
		for (Map<String, Object> map : resultList) {
			String id = (String) map.get("id");
			caseInfoRequestLogCity.setId(id);
			CaseInfoFileCity caseInfoFileCity = new CaseInfoFileCity();
			caseInfoFileCity.setRequestLog(caseInfoRequestLogCity);
			ExampleMatcher matcher = ExampleMatcher.matchingAll();
			Example<CaseInfoFileCity> example = Example.of(caseInfoFileCity, matcher);
			List<CaseInfoFileCity> files = caseInfoFileCityRepository.findAll(example);
			map.put("files", files);
		}
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
	}
	
//	@Override
//	public Object find(CaseInfoRequestLogCity caseInfoRequestLogCity,Page page) {
//		// TODO Auto-generated method stu
//		// 创建匹配器，需要查询条件请修改此处代码
//		ExampleMatcher matcher = ExampleMatcher.matchingAll();
//		// 创建实例
//		Example<CaseInfoRequestLogCity> example = Example.of(caseInfoRequestLogCity, matcher);
//		Long total = caseInfoRequestLogCityRepository.count(example);
//		// 分页构造
//		Pageable pageable = null;
//		Object list = new ArrayList<>();
//		int totalPage = 0;
//		if (page.getPageNo() != null && page.getPageSize() !=null ) {
//			List<Sort.Order> orders = new ArrayList<>();
//			orders.add(new Sort.Order(Sort.Direction.ASC,"createTime"));
//			Sort sort = Sort.by(orders);
//			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize(),sort); // pageIndex默认从0开始
//			list = caseInfoRequestLogCityRepository.findAll(example, pageable).getContent();
//			totalPage = (int) (total % page.getPageSize() == 0 ? total / page.getPageSize() : total / page.getPageSize() + 1);
//		} else {
//			list = caseInfoRequestLogCityRepository.findAll(example);
//		}
//		Map<String, Object> result = new HashMap<>();
//		result.put("data", list);
//		result.put("pageNo", page.getPageNo()); 
//		result.put("pageSize", page.getPageSize());
//		result.put("totalCount", total);
//		result.put("totalPage", totalPage);
//		return new BaseResponse<>(200, "success", result);
//	}

}
