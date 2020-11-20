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
import com.plat.caseinfo.dao.UserPositionRepository;
import com.plat.caseinfo.entity.UserPosition;

@Service
public class UserPositionServiceImpl implements UserPositionService {

	@Autowired
	UserPositionRepository userPositionRepository;
	
	@PersistenceContext 
    EntityManager entityManager;

	@Override
	public Object save(UserPosition userPosition) {
		// TODO Auto-generated method stub
		userPosition.setUploadTime(TimeUtil.getNowTime());
		return new BaseResponse<>(200, "success", userPositionRepository.save(userPosition));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		// 删除之前判断是否还有子节点
		userPositionRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(UserPosition target) {
		// TODO Auto-generated method stub
		Optional<UserPosition> source = userPositionRepository.findById(target.getId());
		if (source.isPresent()) {
			UserPosition userPosition = (UserPosition) BeanProcessUtils.copy(source.get(), target);
			UserPosition result = userPositionRepository.save(userPosition);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(String userid,String startTime,String endTime, Page page) {
		Integer pageNo = page.getPageNo();
		Integer pageSize = page.getPageSize();
		String countSql = " SELECT * from UserPosition where 1=1   ";
		if (!StringUtils.isEmpty(userid)) {
			countSql += " and userid='"+userid+"'";
		}
		if (!StringUtils.isEmpty(startTime)) {
			countSql += " and uploadTime>='"+startTime+"'";
		}
		if (!StringUtils.isEmpty(endTime)) {
			countSql += " and uploadTime<='"+endTime+"'";
		} 
		countSql += " order by uploadtime";
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
	}

	@Override
	public List<Map<String, String>> getRecentPosition() {
		// TODO Auto-generated method stub
		return userPositionRepository.getRecentPosition();
	}


}
