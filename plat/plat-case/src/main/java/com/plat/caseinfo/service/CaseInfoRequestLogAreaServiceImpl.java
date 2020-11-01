package com.plat.caseinfo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.plat.common.dao.UserRepository;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.entity.User;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.common.utils.JwtUtils;
import com.plat.common.utils.TimeUtil;
import com.alibaba.fastjson.JSONObject;
import com.plat.caseinfo.dao.CaseInfoAreaRepository;
import com.plat.caseinfo.dao.CaseInfoCityRepository;
import com.plat.caseinfo.dao.CaseInfoRequestLogAreaRepository;
import com.plat.caseinfo.dao.CaseInfoStreetRepository;
import com.plat.caseinfo.entity.CaseInfoArea;
import com.plat.caseinfo.entity.CaseInfoCity;
import com.plat.caseinfo.entity.CaseInfoFileArea;
import com.plat.caseinfo.entity.CaseInfoRequestLogArea;
import com.plat.caseinfo.entity.CaseInfoStreet;
import com.plat.caseinfo.web.WebSocketConfig;
import com.plat.caseinfo.web.WebSocketServerConfig;

@Service
public class CaseInfoRequestLogAreaServiceImpl implements CaseInfoRequestLogAreaService {

	@Autowired
	CaseInfoRequestLogAreaRepository caseInfoRequestLogAreaRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CaseInfoAreaRepository caseInfoAreaRepository;
	
	@Autowired
	CaseInfoCityRepository caseInfoCityRepository;
	
	@Autowired
	CaseInfoStreetRepository caseInfoStreetRepository;

	@Autowired
	CaseInfoAreaService caseInfoAreaService;
	@Autowired WebSocketServerConfig webSocketServerConfig;
	@Override
	public Object save(CaseInfoRequestLogArea caseInfoRequestLogArea,HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = request.getHeader("token");
		String username = JwtUtils.getClaimFiled(token, "username");
		User user = userRepository.getUserByUsername(username);
		int status = caseInfoRequestLogArea.getStatus();//当前状态
		String result = caseInfoRequestLogArea.getResult();//处理结果
		String caseId = caseInfoRequestLogArea.getCaseid();//案件id
		//更新区局案件表
		CaseInfoArea caseInfoArea = caseInfoAreaRepository.getOne(caseId);
		String casesource1 = caseInfoArea.getCasesource1();
		String laststatus = caseInfoArea.getLaststatus();
		caseInfoArea.setStatus(result.equals("laststatus") ? Integer.parseInt(laststatus) : Integer.parseInt(result));
		caseInfoArea.setLaststatus(status+"");
		caseInfoArea.setOperator(user.getId());
		caseInfoArea.setOperatetime(TimeUtil.getNowTime());
		if (result.equals("1")) { //上报提交
			
		} else if (result.equals("2")) { //立案
			String caseNo = caseInfoAreaRepository.getCaseFlowCode(casesource1.equals("内部") ? "N" : "W", 8);
			if (caseInfoArea.getCaseNo() == null) {
				caseInfoArea.setCaseNo(caseNo);
				caseInfoArea.setSetupdate(TimeUtil.getNowTime());
			}
			// 刷新前端指挥中心大屏列表
			Object object = caseInfoAreaService.find2(2, new Page(1, 10), "");
			webSocketServerConfig.sendAllInfo(JSONObject.toJSONString(object));
			
		} else if (result.equals("3")) { //分派
			caseInfoArea.setAssigndate(TimeUtil.getNowTime());
			
		} else if (result.equals("4")) { //处置反馈件--指挥中心
			caseInfoArea.setDealdate(TimeUtil.getNowTime());
			
		} else if (result.equals("5")) { //案件核查
			
		} else if (result.equals("6")) { //核查反馈件
			caseInfoArea.setVerifydate(TimeUtil.getNowTime());
			
		} else if (result.equals("7")) { //申请回退
			
		} else if (result.equals("8")) { //申请延期
			
		} else if (result.equals("10")) { //结案
			caseInfoArea.setClosedate(TimeUtil.getNowTime());
			
		} else if (result.equals("9")) { //市局案件
			
		} 
		caseInfoAreaRepository.save(caseInfoArea);
		//更新市局案件表
		if (caseInfoArea.getCityCaseId() != null 
				&& (result.equals("7") || result.equals("8") || result.equals("10"))) { 
			CaseInfoCity caseInfoCity = caseInfoCityRepository.getOne(caseInfoArea.getCityCaseId());
			caseInfoCity.setStatus(Integer.parseInt(result));
			caseInfoCity.setLaststatus(status+"");
			caseInfoCity.setOperator(user.getId());
			caseInfoCity.setOperatetime(TimeUtil.getNowTime());
			caseInfoCityRepository.save(caseInfoCity);
		}
		//更新流转表
		caseInfoRequestLogArea.setNodeid(status+"");//当前节点
		caseInfoRequestLogArea.setDestnodeid(result);//下一节点
		caseInfoRequestLogArea.setOperator(user.getId());//操作人
		caseInfoRequestLogArea.setOperatetime(TimeUtil.getNowTime());//操作时间
		List<CaseInfoFileArea> files =  caseInfoRequestLogArea.getFiles();
		if (files != null) {
			for (CaseInfoFileArea file : files) {
				file.setRequestLog(caseInfoRequestLogArea);
			}
		}
		if (caseInfoRequestLogArea.getId() != null) {
			Optional<CaseInfoRequestLogArea> source = caseInfoRequestLogAreaRepository.findById(caseInfoRequestLogArea.getId());
			if (source.isPresent()) {
				caseInfoRequestLogArea = (CaseInfoRequestLogArea) BeanProcessUtils.copy(source.get(), caseInfoRequestLogArea);
				
			}
		}
		caseInfoRequestLogArea = caseInfoRequestLogAreaRepository.save(caseInfoRequestLogArea);
		if (result.equals("3")) { //分派
			// 主办人、协办人
			String sponsor = caseInfoRequestLogArea.getSponsor();
			if (sponsor != null) {
				User sponsorTemp = userRepository.getOne(sponsor);
				CaseInfoRequestLogArea temp = new CaseInfoRequestLogArea();
				temp.setCaseid(caseId);
				temp.setNodeid(result+"");
				// temp.setDestnodeid(result);
				temp.setOperator(sponsor);//操作人
				if (sponsorTemp != null) { //人员
					temp.setOperatorType(0);
					caseInfoRequestLogAreaRepository.save(temp);
				} else { //分部
					temp.setOperatorType(3); //下派
					caseInfoRequestLogAreaRepository.save(temp);
					// 往街道表添加案件记录
					CaseInfoStreet caseInfoStreet = new CaseInfoStreet();
					BeanProcessUtils.copy(caseInfoArea,caseInfoStreet);
					caseInfoStreet.setId(null);
					caseInfoStreet.setStatus(9);
					caseInfoStreet.setAreaCaseId(caseInfoArea.getId());
					caseInfoStreetRepository.save(caseInfoStreet);
					
				}
			}
			String coOrganizer = caseInfoRequestLogArea.getCoOrganizer();
			if (coOrganizer != null) {
				User coOrganizerTemp = userRepository.getOne(coOrganizer);
				CaseInfoRequestLogArea temp = new CaseInfoRequestLogArea();
				temp.setCaseid(caseId);
				temp.setNodeid(result+"");
				// temp.setDestnodeid(result);
				temp.setOperator(coOrganizer);//操作人
				if (coOrganizerTemp != null) { //人员
					temp.setOperatorType(1);
					caseInfoRequestLogAreaRepository.save(temp);
				} else { //分部
					temp.setOperatorType(3); //下派
					caseInfoRequestLogAreaRepository.save(temp);
					// 往街道表添加案件记录
					CaseInfoStreet caseInfoStreet = new CaseInfoStreet();
					BeanProcessUtils.copy(caseInfoArea,caseInfoStreet);
					caseInfoStreet.setId(null);
					caseInfoStreet.setStatus(9);
					caseInfoStreet.setAreaCaseId(caseInfoArea.getId());
					caseInfoStreetRepository.save(caseInfoStreet);
					
				}
			}
			
		} else if (result.equals("5")) { //案件核查
			//核查人
			String verifier = caseInfoRequestLogArea.getVerifier();
			if (verifier != null) {
				User verifierTemp = userRepository.getOne(verifier);
				CaseInfoRequestLogArea temp = new CaseInfoRequestLogArea();
				temp.setCaseid(caseId);
				temp.setNodeid(result+"");
				// temp.setDestnodeid(result);
				temp.setOperator(verifier);//操作人
				if (verifierTemp != null) { //人员
					temp.setOperatorType(2);
					caseInfoRequestLogAreaRepository.save(temp);
				} else { //分部
					temp.setOperatorType(3); //下派
					caseInfoRequestLogAreaRepository.save(temp);
					// 往街道表添加案件记录
					CaseInfoStreet caseInfoStreet = new CaseInfoStreet();
					BeanProcessUtils.copy(caseInfoArea,caseInfoStreet);
					caseInfoStreet.setId(null);
					caseInfoStreet.setStatus(9);
					caseInfoStreet.setAreaCaseId(caseInfoArea.getId());
					caseInfoStreetRepository.save(caseInfoStreet);
					
				}
			}
		}
		return new BaseResponse<>(200, "success");
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		caseInfoRequestLogAreaRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(CaseInfoRequestLogArea target) {
		// TODO Auto-generated method stub
		Optional<CaseInfoRequestLogArea> source = caseInfoRequestLogAreaRepository.findById(target.getId());
		if (source.isPresent()) {
			CaseInfoRequestLogArea caseInfoRequestLogArea = (CaseInfoRequestLogArea) BeanProcessUtils.copy(source.get(), target);
			List<CaseInfoFileArea> files =  caseInfoRequestLogArea.getFiles();
			for (CaseInfoFileArea file : files) {
				file.setRequestLog(caseInfoRequestLogArea);
			}
			CaseInfoRequestLogArea result = caseInfoRequestLogAreaRepository.save(caseInfoRequestLogArea);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(CaseInfoRequestLogArea caseInfoRequestLogArea,Page page) {
		// TODO Auto-generated method stu
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		// 创建实例
		Example<CaseInfoRequestLogArea> example = Example.of(caseInfoRequestLogArea, matcher);
		Long total = caseInfoRequestLogAreaRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Object list = new ArrayList<>();
		int totalPage = 0;
		if (page.getPageNo() != null && page.getPageSize() !=null ) {
			List<Sort.Order> orders = new ArrayList<>();
			orders.add(new Sort.Order(Sort.Direction.ASC,"createTime"));
			Sort sort = Sort.by(orders);
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize(),sort); // pageIndex默认从0开始
			list = caseInfoRequestLogAreaRepository.findAll(example, pageable).getContent();
			totalPage = (int) (total % page.getPageSize() == 0 ? total / page.getPageSize() : total / page.getPageSize() + 1);
		} else {
			list = caseInfoRequestLogAreaRepository.findAll(example);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("data", list);
		result.put("pageNo", page.getPageNo()); 
		result.put("pageSize", page.getPageSize());
		result.put("totalCount", total);
		result.put("totalPage", totalPage);
		return new BaseResponse<>(200, "success", result);
	}

}
