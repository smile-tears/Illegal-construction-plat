package com.plat.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.common.dao.UserRoleRepository;
import com.plat.common.entity.UserRole;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleRepository userRoleRepository;
	
	@PersistenceContext 
    EntityManager entityManager;

	@Override
	public Object save(Iterable<UserRole> entities) {
		// TODO Auto-generated method stub
		userRoleRepository.saveAll(entities);
		return new BaseResponse<>(200, "success", "保存成功");
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		userRoleRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object find(String roleId,String name,Integer pageNo,Integer pageSize) {
		// TODO Auto-generated method stub
		Integer start = (pageNo - 1 ) * pageSize;
		Integer offset = pageSize;
		String countSql = "SELECT t2.id,t1.name,t1.username from user t1,user_role t2 "
				+ " where t1.id=t2.userid and t2.roleId='"+roleId+"' ";
		if (!StringUtils.isEmpty(name)) {
			countSql += " and t1.name like '%"+name+"%'";
		}
		String dataSql = countSql;
		if (!StringUtils.isEmpty(pageNo) && !StringUtils.isEmpty(pageSize)) {
			dataSql += " limit " + start + "," + offset;
		}
		int total = entityManager.createNativeQuery(countSql).getResultList().size();
		List<Object[]> resultList = entityManager.createNativeQuery(dataSql).getResultList();
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		for (Object[] objects : resultList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", objects[0]);
			map.put("name", objects[1]);
			map.put("username", objects[2]);
			dataList.add(map);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("data", dataList);
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
