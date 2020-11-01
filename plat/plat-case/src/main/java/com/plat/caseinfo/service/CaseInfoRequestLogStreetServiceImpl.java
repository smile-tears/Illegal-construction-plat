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
import com.plat.caseinfo.dao.CaseInfoStreetRepository;
import com.plat.caseinfo.dao.CaseInfoAreaRepository;
import com.plat.caseinfo.dao.CaseInfoRequestLogStreetRepository;
import com.plat.caseinfo.entity.CaseInfoStreet;
import com.plat.caseinfo.entity.CaseInfoArea;
import com.plat.caseinfo.entity.CaseInfoCity;
import com.plat.caseinfo.entity.CaseInfoFileStreet;
import com.plat.caseinfo.entity.CaseInfoRequestLogStreet;
import com.plat.caseinfo.web.WebSocketConfig;
import com.plat.caseinfo.web.WebSocketServerConfig;

@Service
public class CaseInfoRequestLogStreetServiceImpl implements CaseInfoRequestLogStreetService {

	@Autowired
	CaseInfoRequestLogStreetRepository caseInfoRequestLogStreetRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CaseInfoStreetRepository caseInfoStreetRepository;

	@Autowired
	CaseInfoAreaRepository caseInfoAreaRepository;
	
	@Autowired
	CaseInfoStreetService caseInfoStreetService;
	@Autowired WebSocketServerConfig webSocketServerConfig;
	@Override
	public Object save(CaseInfoRequestLogStreet caseInfoRequestLogStreet,HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = request.getHeader("token");
		String username = JwtUtils.getClaimFiled(token, "username");
		User user = userRepository.getUserByUsername(username);
		int status = caseInfoRequestLogStreet.getStatus();//当前状态
		String result = caseInfoRequestLogStreet.getResult();//处理结果
		String caseId = caseInfoRequestLogStreet.getCaseid();//案件id
		//更新街道案件表
		CaseInfoStreet caseInfoStreet = caseInfoStreetRepository.getOne(caseId);
		String casesource1 = caseInfoStreet.getCasesource1();
		String laststatus = caseInfoStreet.getLaststatus();
		caseInfoStreet.setStatus(result.equals("laststatus") ? Integer.parseInt(laststatus) : Integer.parseInt(result));
		caseInfoStreet.setLaststatus(status+"");
		caseInfoStreet.setOperator(user.getId());
		caseInfoStreet.setOperatetime(TimeUtil.getNowTime());
		if (result.equals("1")) { //上报提交
			
		} else if (result.equals("2")) { //立案
			String caseNo = caseInfoStreetRepository.getCaseFlowCode(casesource1.equals("内部") ? "N" : "W", 8);
			if (caseInfoStreet.getCaseNo() == null) {
				caseInfoStreet.setCaseNo(caseNo);
				caseInfoStreet.setSetupdate(TimeUtil.getNowTime());
			}
			// 刷新前端指挥中心大屏列表
			Object object = caseInfoStreetService.find2(2, new Page(1, 10), "");
			webSocketServerConfig.sendAllInfo(JSONObject.toJSONString(object));
			
		} else if (result.equals("3")) { //分派
			caseInfoStreet.setAssigndate(TimeUtil.getNowTime());
			
		} else if (result.equals("4")) { //处置反馈件--指挥中心
			caseInfoStreet.setDealdate(TimeUtil.getNowTime());
			
		} else if (result.equals("5")) { //案件核查
			
		} else if (result.equals("6")) { //核查反馈件
			caseInfoStreet.setVerifydate(TimeUtil.getNowTime());
			
		} else if (result.equals("7")) { //申请回退
			
		} else if (result.equals("8")) { //申请延期
			
		} else if (result.equals("10")) { //结案
			caseInfoStreet.setClosedate(TimeUtil.getNowTime());
			
		}
		caseInfoStreetRepository.save(caseInfoStreet);
		//更新区局案件表
		if (caseInfoStreet.getAreaCaseId() != null 
				&& (result.equals("7") || result.equals("8") || result.equals("10"))) { 
			CaseInfoArea caseInfoArea = caseInfoAreaRepository.getOne(caseInfoStreet.getAreaCaseId());
			caseInfoArea.setStatus(Integer.parseInt(result));
			caseInfoArea.setLaststatus(status+"");
			caseInfoArea.setOperator(user.getId());
			caseInfoArea.setOperatetime(TimeUtil.getNowTime());
			caseInfoAreaRepository.save(caseInfoArea);
		}
		//更新流转表
		caseInfoRequestLogStreet.setNodeid(status+"");//当前节点
		caseInfoRequestLogStreet.setDestnodeid(result);//下一节点
		caseInfoRequestLogStreet.setOperator(user.getId());//操作人
		caseInfoRequestLogStreet.setOperatetime(TimeUtil.getNowTime());//操作时间
		List<CaseInfoFileStreet> files =  caseInfoRequestLogStreet.getFiles();
		if (files != null) {
			for (CaseInfoFileStreet file : files) {
				file.setRequestLog(caseInfoRequestLogStreet);
			}
		}
		if (caseInfoRequestLogStreet.getId() != null) {
			Optional<CaseInfoRequestLogStreet> source = caseInfoRequestLogStreetRepository.findById(caseInfoRequestLogStreet.getId());
			if (source.isPresent()) {
				caseInfoRequestLogStreet = (CaseInfoRequestLogStreet) BeanProcessUtils.copy(source.get(), caseInfoRequestLogStreet);
				
			}
		}
		caseInfoRequestLogStreet = caseInfoRequestLogStreetRepository.save(caseInfoRequestLogStreet);
		if (result.equals("3")) { //分派
			// 主办人、协办人
			String sponsor = caseInfoRequestLogStreet.getSponsor();
			if (sponsor != null) {
				CaseInfoRequestLogStreet temp = new CaseInfoRequestLogStreet();
				temp.setCaseid(caseId);
				temp.setNodeid(result+"");
				// temp.setDestnodeid(result);
				temp.setOperator(sponsor);//操作人
				temp.setOperatorType(0);
				caseInfoRequestLogStreetRepository.save(temp);
			}
			String coOrganizer = caseInfoRequestLogStreet.getCoOrganizer();
			if (coOrganizer != null) {
				CaseInfoRequestLogStreet temp = new CaseInfoRequestLogStreet();
				temp.setCaseid(caseId);
				temp.setNodeid(result+"");
				// temp.setDestnodeid(result);
				temp.setOperator(coOrganizer);//操作人
				temp.setOperatorType(1);
				caseInfoRequestLogStreetRepository.save(temp);
			}
			
		} else if (result.equals("5")) { //案件核查
			//核查人
			String verifier = caseInfoRequestLogStreet.getVerifier();
			if (verifier != null) {
				CaseInfoRequestLogStreet temp = new CaseInfoRequestLogStreet();
				temp.setCaseid(caseId);
				temp.setNodeid(result+"");
				// temp.setDestnodeid(result);
				temp.setOperator(verifier);//操作人
				temp.setOperatorType(2);
				caseInfoRequestLogStreetRepository.save(temp);
			}
		}
		return new BaseResponse<>(200, "success");
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		caseInfoRequestLogStreetRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(CaseInfoRequestLogStreet target) {
		// TODO Auto-generated method stub
		Optional<CaseInfoRequestLogStreet> source = caseInfoRequestLogStreetRepository.findById(target.getId());
		if (source.isPresent()) {
			CaseInfoRequestLogStreet caseInfoRequestLogStreet = (CaseInfoRequestLogStreet) BeanProcessUtils.copy(source.get(), target);
			List<CaseInfoFileStreet> files =  caseInfoRequestLogStreet.getFiles();
			for (CaseInfoFileStreet file : files) {
				file.setRequestLog(caseInfoRequestLogStreet);
			}
			CaseInfoRequestLogStreet result = caseInfoRequestLogStreetRepository.save(caseInfoRequestLogStreet);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(CaseInfoRequestLogStreet caseInfoRequestLogStreet,Page page) {
		// TODO Auto-generated method stu
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		// 创建实例
		Example<CaseInfoRequestLogStreet> example = Example.of(caseInfoRequestLogStreet, matcher);
		Long total = caseInfoRequestLogStreetRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Object list = new ArrayList<>();
		int totalPage = 0;
		if (page.getPageNo() != null && page.getPageSize() !=null ) {
			List<Sort.Order> orders = new ArrayList<>();
			orders.add(new Sort.Order(Sort.Direction.ASC,"createTime"));
			Sort sort = Sort.by(orders);
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize(),sort); // pageIndex默认从0开始
			list = caseInfoRequestLogStreetRepository.findAll(example, pageable).getContent();
			totalPage = (int) (total % page.getPageSize() == 0 ? total / page.getPageSize() : total / page.getPageSize() + 1);
		} else {
			list = caseInfoRequestLogStreetRepository.findAll(example);
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
