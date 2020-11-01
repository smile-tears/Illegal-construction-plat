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
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.common.utils.TimeUtil;
import com.plat.caseinfo.dao.UserTrackRepository;
import com.plat.caseinfo.entity.UserTrack;

@Service
public class UserTrackServiceImpl implements UserTrackService {

	@Autowired
	UserTrackRepository userTrackRepository;
	
	@PersistenceContext 
    EntityManager entityManager;

	@Override
	public Object save(UserTrack userTrack) {
		// TODO Auto-generated method stub
		userTrack.setUploadTime(TimeUtil.getNowTime());
		return new BaseResponse<>(200, "success", userTrackRepository.save(userTrack));
	}



	@Override
	public Object find(String userid,String starttime,String endtime, Page page) {
		// TODO Auto-generated method stub
		Integer pageNo = page.getPageNo();
		Integer pageSize = page.getPageSize();
		// TODO Auto-generated method stub
		Integer start = (pageNo - 1 ) * pageSize;
		Integer offset = pageSize;
		String countSql = " select lng,lat from userTrack where 1=1 ";
		if (!StringUtils.isEmpty(userid)) {
			countSql += " and userid='"+userid+"'";
			if (!StringUtils.isEmpty(starttime)) {
				countSql += " and uploadTime>='"+starttime+"'";
			} 
			if (!StringUtils.isEmpty(endtime)) {
				countSql += " and uploadTime<='"+endtime+"'";
			}
		} else {
			
		}
		 

		countSql += " ORDER BY uploadTime desc ";
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
	}

}
