package com.plat.caseinfo.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.plat.common.utils.StringUtil;
import com.plat.common.utils.TimeUtil;
import com.plat.common.utils.WordUtil;
import com.plat.common.web.FileController;
import com.plat.sysconfig.dao.QuestionTypeRepository;
import com.plat.sysconfig.dao.SysGlobalConfigRepository;
import com.plat.sysconfig.entity.SysGlobalConfig;
import com.plat.sysconfig.util.IntervalTimeUtil;
import com.plat.caseinfo.entity.CaseInfoCity;
import com.plat.caseinfo.entity.CaseQuestion;
import com.alibaba.fastjson.JSON;
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

	@Autowired
	SysGlobalConfigRepository sysGlobalConfigRepository;
	
	@Autowired
	QuestionTypeRepository questionTypeRepository;
	
	public Integer getCalculateType() {
		int type = 1;
		List<SysGlobalConfig> sysGlobalConfigs = sysGlobalConfigRepository.findAll();
		if (sysGlobalConfigs.size() >0 ) {
			type = sysGlobalConfigs.get(0).getCalculateTypeOfWorkTime();
		}
		return type;
	}
	
	@Override
	public Object save(CaseInfoCity caseInfoCity) {
		// TODO Auto-generated method stub
		if (StringUtils.isEmpty(caseInfoCity.getLng()) ||StringUtils.isEmpty(caseInfoCity.getLat()) ) {
			return new BaseResponse<>(500, "经纬度坐标不全！");
		}
		List<CaseQuestion> caseQuestions = caseInfoCity.getCaseQuestions();
		for (CaseQuestion caseQuestion : caseQuestions) {
			caseQuestion.setCaseInfoCity(caseInfoCity);
		}
		try {
			if (caseInfoCity.getStatus() == 1) {
				String currentTime = TimeUtil.getNowTime();
				caseInfoCity.setReportTime(currentTime);
				String endDate = IntervalTimeUtil.getEndDate(currentTime,
						caseInfoCity.getLimittimes() * 24 , getCalculateType());
				caseInfoCity.setEndDate(endDate);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public Object Update(CaseInfoCity target) { // double类型会映射成0.0
		// TODO Auto-generated method stub
//		if (StringUtils.isEmpty(target.getLng()) ||StringUtils.isEmpty(target.getLat()) ) {
//			return new BaseResponse<>(500, "经纬度坐标不全！");
//		}
		Optional<CaseInfoCity> source = caseInfoCityRepository.findById(target.getId());
		if (source.isPresent()) {
			//System.out.println("=======source="+JSON.toJSONString(source));
			CaseInfoCity caseInfoCity = (CaseInfoCity) BeanProcessUtils.copy(source.get(), target);
			if (target.getLimittimes() == 0) { // double类型会映射成0.0
				caseInfoCity.setLimittimes(source.get().getLimittimes());
			}
			//System.out.println("=======result="+JSON.toJSONString(caseInfoCity));
			try {
				if (caseInfoCity.getStatus() == 1) {
					String currentTime = TimeUtil.getNowTime();
					caseInfoCity.setReportTime(currentTime);
					String endDate = IntervalTimeUtil.getEndDate(currentTime,
							caseInfoCity.getLimittimes() , getCalculateType());
					caseInfoCity.setEndDate(endDate);
				} else if (caseInfoCity.getStatus() == 2) {
					String currentTime = TimeUtil.getNowTime();
					caseInfoCity.setHandleDate(currentTime);
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	public JSONObject find2(CaseInfoCity caseInfoCity, Page page,HttpServletRequest request ) {
	    //String currentUserId = "";
		//if (request != null) currentUserId = userService.getUserByToken(request).getId();
		Integer pageNo = page.getPageNo();
		Integer pageSize = page.getPageSize();
		// TODO Auto-generated method stub
		
		String countSql = " SELECT t1.*,t6.typeName,t6.typeName2,t2.companyName,t3.gridName,t4.name reportorName,t5.name managerName, " + 
				" case when timestampdiff(SECOND,NOW(),enddate)/timestampdiff(SECOND,reportTime,enddate) >= (1/3) then '0' " + 
				" when timestampdiff(SECOND,NOW(),enddate)/timestampdiff(SECOND,reportTime,enddate) >= 0 then '1' " + 
				" when timestampdiff(SECOND,NOW(),enddate)/timestampdiff(SECOND,reportTime,enddate) < 0 then '2' " + 
				" end as state " +
				"from caseinfo_city t1 " + 
				"LEFT JOIN companymanage t2 on t1.companyid=t2.id " + 
				"LEFT JOIN gridcommunity t3 on t2.grid=t3.id " + 
				"LEFT JOIN user t4 on t1.reportor=t4.id "+
				"LEFT JOIN user t5 on t1.manager=t5.id "+
				" LEFT JOIN (SELECT caseInfoCityId,GROUP_CONCAT(t2.typeName) as typeName," +
				" GROUP_CONCAT(distinct t3.typeName) as typeName2 "+
				" from casequestion t1 " + 
				" LEFT JOIN questiontype t2 on t1.questiontype=t2.id "+
				" LEFT JOIN questiontype t3 on t1.fstLvlType=t3.id "+
				" GROUP BY caseInfoCityId ) t6 on t1.id=t6.caseInfoCityId " + 
				" where 1=1 ";
		if (!StringUtils.isEmpty(caseInfoCity.getId())) {
			countSql += " and t1.id='"+caseInfoCity.getId()+"'";
		}
		if (!StringUtils.isEmpty(caseInfoCity.getStatus())) {
			if (caseInfoCity.getStatus() == 0 ) { // 案件待上报
				countSql += " and t1.status=0 ";
				if (!StringUtils.isEmpty(caseInfoCity.getReportor())) {
					countSql += " and t1.reportor='"+caseInfoCity.getReportor()+"'";
				}
			} else if (caseInfoCity.getStatus() == 1) { // 案件待处置
				countSql += " and t1.status=1 ";
				if (!StringUtils.isEmpty(caseInfoCity.getReportor())) {
					countSql += " and t1.reportor='"+caseInfoCity.getReportor()+"'";
				}
			} else if (caseInfoCity.getStatus() == 2) { // 综合查询
				countSql += " and t1.status <>0 ";
			}
		}
		
		
		if (!StringUtils.isEmpty(caseInfoCity.getTitle())) {
			countSql += " and t1.title like '%"+caseInfoCity.getTitle()+"%'";
		}
		countSql += " ORDER BY t1.reportTime desc ";
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
		JSONObject result = new JSONObject();
		result.put("data", resultList);
		result.put("pageNo", pageNo); 
		result.put("pageSize", pageSize);
		result.put("totalCount", total);
		if (pageNo == null || pageSize ==null) {
			result.put("totalPage",1);
		} else {
			result.put("totalPage", total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
		}
		//return new BaseResponse<>(200, "success", result);
		return result;
	}

	@Override
	public Object report() {
		// TODO Auto-generated method stub
		Map<String, Integer> result = new HashMap<>();
		Integer jrsbs = 0;
		Integer jrczs = 0;
		Integer jrdcz = 0;
		List<Map<String, Object>> list = caseInfoCityRepository.report();
		for (Iterator<Map<String, Object>> iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			//System.out.println("======"+JSONObject.toJSONString(map));
			Integer status = (Integer)map.get("status");
			if (status == 2) {
				jrczs = Integer.parseInt(String.valueOf(map.get("num")));
			} else if(status == 1) {
				jrsbs = Integer.parseInt(String.valueOf(map.get("num")));
				jrdcz = jrsbs;
			}
		}
		jrsbs = jrsbs + jrczs;
		result.put("今日处置数", jrczs);
		result.put("今日上报数", jrsbs);
		result.put("今日待处置数", jrdcz);
		return result;
	}

	@Override
	public Object report2(String startDate,String endDate,String grid,String manager) {
		// TODO Auto-generated method stub
		
		String sql1 = "select t.*,concat( FORMAT(dealedNum * 100.0 / reportNum ,2), '%')as dealedPercent, "
				+" concat( FORMAT(overtimeNum * 100.0 / reportNum ,2), '%') as overtimePercent  " + 
				"from ( " + 
				"SELECT t3.name manager,t2.gridName,count(1) reportNum,sum(case t1.status when 2 then 1 else 0 end) dealedNum,  " + 
				"sum(    " + 
				"case when handledate is null then  " + 
				"	(case when date_format(now(),'%Y-%m-%d %H:%m:%s') > enddate then 1 else 0 end) " + 
				"ELSE " + 
				"	(case when handledate>enddate then 1 else 0 end) " + 
				"end  " + 
				") overtimeNum , 1 as rowSpan" + 
				" from caseinfo_city t1    " + 
				"JOIN gridcommunity t2 on instr(t2.patrolManager,t1.reportor) > 0   " + 
				"LEFT JOIN user t3 on t1.reportor=t3.id   " + 
				"where t1.status<>0   "; 
		if (!StringUtils.isEmpty(startDate)) {
			sql1 += " and substr(t1.reportTime,1,10)>='"+startDate+"'";
		}
		if (!StringUtils.isEmpty(endDate)) {
			sql1 += " and substr(t1.reportTime,1,10)<='"+startDate+"'";
		}
		if (!StringUtils.isEmpty(grid)) {
			sql1 += " and t2.id='"+grid+"'";
		}
		if (!StringUtils.isEmpty(manager)) {
			sql1 += " and t1.reportor='"+manager+"'";
		}
		sql1 += "GROUP BY t3.name,t2.gridName ORDER BY t2.gridName,t3.name) t  ";

		List<Map<String, String>> data1 = entityManager.createNativeQuery(sql1).unwrap(NativeQueryImpl.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
		List<String> gridNameList = new ArrayList<>();
		for(int i=0; i<data1.size(); i++) {
			Map<String, String> map = data1.get(i);
			String gridName = map.get("gridName");
			if (gridNameList.contains(gridName)) {
				map.put("rowSpan", "0");
			} else {
				gridNameList.add(gridName);
				int index = 0;
				for(int j=i; j<data1.size(); j++) {
					if (data1.get(j).get("gridName").equals(gridName)) {
						index++;
					} else {
						break;
					}
				}
				map.put("rowSpan", index+"");
			}
		}
		String sql2 = "SELECT t4.questiontype,t2.typeName,t2.typeName as item,count(1) count from caseinfo_city t1 " + 
				" left join casequestion t4 on t4.caseInfoCityId = t1.id" +
				" LEFT JOIN questiontype t2 on t4.questiontype=t2.id " + 
				" left JOIN gridcommunity t3 on instr(t3.patrolManager,t1.reportor) > 0   " + 
				" where t1.status<>0  ";
		if (!StringUtils.isEmpty(startDate)) {
			sql2 += " and substr(t1.reportTime,1,10)>='"+startDate+"'";
		}
		if (!StringUtils.isEmpty(endDate)) {
			sql2 += " and substr(t1.reportTime,1,10)<='"+startDate+"'";
		}
		if (!StringUtils.isEmpty(grid)) {
			sql2 += " and t3.id='"+grid+"'";
		}
		if (!StringUtils.isEmpty(manager)) {
			sql2 += " and t1.reportor='"+manager+"'";
		}
		sql2 += " GROUP BY t4.questiontype,t2.typeName,t2.showorder order by t2.showorder";
		List<Map<String, String>> data2 = entityManager.createNativeQuery(sql2).unwrap(NativeQueryImpl.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
		
		String areaSql = "SELECT id,gridName,patrolManager from gridcommunity "
				+" where patrolManager is not null and patrolManager<>'' " ;
		if (!StringUtils.isEmpty(grid)) {
			areaSql += " and id='"+grid+"'";
		}
		areaSql += " ORDER BY showOrder ";
		List<Map<String, String>> areaList = entityManager.createNativeQuery(areaSql).unwrap(NativeQueryImpl.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
		
		String sql3 = " SELECT t1.showOrder,t1.typeName as name"; 
		for (int i=0 ;i <areaList.size(); i++) {
			Map<String, String> area = areaList.get(i);
			sql3 += "  ,( select count(1) from caseinfo_city t "
					+" JOIN gridcommunity t3 on instr(t3.patrolManager,t.reportor) > 0 "
					+" left join casequestion t4 on t4.caseInfoCityId=t.id  "
					+" where t3.id='"+area.get("id")+"' and t4.questiontype=t1.id ";
			if (!StringUtils.isEmpty(startDate)) {
				sql3 += " and substr(t.reportTime,1,10)>='"+startDate+"'";
			}
			if (!StringUtils.isEmpty(endDate)) {
				sql3 += " and substr(t.reportTime,1,10)<='"+startDate+"'";
			}
			if (!StringUtils.isEmpty(grid)) {
				sql3 += " and t3.id='"+grid+"'";
			}
			if (!StringUtils.isEmpty(manager)) {
				sql3 += " and t.reportor='"+manager+"'";
			}
			sql3 += " ) as "+area.get("gridName");
		}
		sql3 += " from questiontype t1 GROUP BY t1.typeName,t1.showOrder order by t1.showOrder";
		//System.out.println("==========="+sql3);
		List<Map<String, String>> data3 = entityManager.createNativeQuery(sql3).unwrap(NativeQueryImpl.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
		
		for (int i=0; i<data2.size(); i++) {
			Map<String, String> map = data2.get(i);
			map.remove("typeName");
			map.remove("questiontype");
		}
		for (int i=0; i<data3.size(); i++) {
			Map<String, String> map = data3.get(i);
			map.remove("showOrder");
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tableData", data1);
		jsonObject.put("pieData", data2);
		jsonObject.put("barData", data3);
//		System.out.println("========="+sql1);
//		System.out.println("========="+sql2);
//		System.out.println("========="+sql3);
		return jsonObject;
	}

	@Override
	public Object exportWord(String id) {
		// TODO Auto-generated method stub
		CaseInfoCity caseInfoCity = new CaseInfoCity();
		caseInfoCity.setId(id);
		JSONObject json = find2(caseInfoCity, new Page(), null);
		JSONArray jsonArray = json.getJSONArray("data");
		JSONObject jo = new JSONObject();
		if (jsonArray.size() > 0) jo = jsonArray.getJSONObject(0);
		//System.out.println("========"+jo.toJSONString());
		Map<String, Object> map = new HashMap<>();
		String reportTime = jo.getString("reportTime");
		map.put("year", reportTime.substring(0,4));
		map.put("month", reportTime.substring(5,7));
		
//		String questionType = StringUtil.null2String(jo.getString("questionType"));
//		String typeName = "";
//		if (!"".equals(questionType)) {
//			typeName = questionTypeRepository.getOne(questionType).getTypeName();
//		}
		map.put("typeName",StringUtil.null2String(jo.getString("typeName")));
		map.put("companyName", StringUtil.null2String(jo.getString("companyName")));
		map.put("locationDesc", StringUtil.null2String(jo.getString("locationDesc")));
		map.put("manager", StringUtil.null2String(jo.getString("manager")));
		map.put("managerMobile", StringUtil.null2String(jo.getString("managerMobile")));
		map.put("reportor", StringUtil.null2String(jo.getString("reportorName")));
		map.put("reportorMobile", StringUtil.null2String(jo.getString("reportorMobile")));
		map.put("site", StringUtil.null2String(jo.getString("site")));
		map.put("startTime", StringUtil.null2String(jo.getString("startTime")));
		map.put("endTime", StringUtil.null2String(jo.getString("endTime")));
		map.put("caseDesc", StringUtil.null2String(jo.getString("caseDesc")));
		
		//处置前图片
		List<String> imageList1 = new ArrayList<>();
		map.put("imageList1", imageList1);
		String imagePath = StringUtil.null2String(jo.getString("imagePath"));
		String[] imgPathArr = imagePath.split(",");
		for (String path : imgPathArr) {
			if ("".equals(path)) continue;
			path = path.replace("/avatar/", "");
			imageList1.add(WordUtil.getImageBase(FileController.imageAbsolute + path));
		}
		//处置后图片
		map.put("imageList2", new ArrayList<>());
		String imagePath2 = StringUtil.null2String(jo.getString("imagePath2"));
		List<String> imageList2 = new ArrayList<>();
		String[] imgPathArr2 = imagePath2.split(",");
		for (String path2 : imgPathArr2) {
			if ("".equals(path2)) continue;
			path2 = path2.replace("/avatar/", "");
			imageList2.add(WordUtil.getImageBase(FileController.imageAbsolute + path2));
		}
		
		String endDate = jo.getString("endDate");
		map.put("year2", endDate.substring(0,4));
		map.put("month2", endDate.substring(5,7));
		map.put("day2", reportTime.substring(8,10));
		
		String currentDate = TimeUtil.getNowTime();
		map.put("year3", currentDate.substring(0,4));
		map.put("month3", currentDate.substring(5,7));
		map.put("day3", currentDate.substring(8,10));
		
		String uuid = jo.getString("id");
		String url = FileController.fileAbsolute + uuid+".doc";
		
		String dir = FileController.fileAbsolute;
		String filename = "word.ftl";
		String result = WordUtil.createWord(url,map,dir,filename);	
		return result.equals("") ? ("/file/" + uuid+".doc") : result;
	}

	
}
