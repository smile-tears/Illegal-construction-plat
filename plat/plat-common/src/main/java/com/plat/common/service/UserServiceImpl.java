package com.plat.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.SimpleHash;
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

import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.common.utils.EncryptionUtils;
import com.plat.common.utils.JwtUtils;
import com.plat.common.dao.DepartmentRepository;
import com.plat.common.dao.UserRepository;
import com.plat.common.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	DepartmentRepository departmentRepository;

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Object save(User user) {
		// TODO Auto-generated method stub
		SimpleHash sh = new SimpleHash(EncryptionUtils.algorithmName, user.getPassword(), EncryptionUtils.salt,
				EncryptionUtils.hashIterations);
		user.setPassword(sh.toHex());
		if (user.getSubCompanyId() == null) {
			String subCompanyId = departmentRepository.getOne(user.getDepartmentId()).getSubCompanyId();
			user.setSubCompanyId(subCompanyId);
		}

		return new BaseResponse<>(200, "success", userRepository.save(user));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		userRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

	@Override
	public Object Update(User target) {
		// TODO Auto-generated method stub
		Optional<User> source = userRepository.findById(target.getId());
		if (source.isPresent()) {
			User user = (User) BeanProcessUtils.copy(source.get(), target);
			SimpleHash sh = new SimpleHash(EncryptionUtils.algorithmName, user.getPassword(), EncryptionUtils.salt,
					EncryptionUtils.hashIterations);
			user.setPassword(sh.toHex());
			if (user.getSubCompanyId() == null) {
				String subCompanyId = departmentRepository.getOne(user.getDepartmentId()).getSubCompanyId();
				user.setSubCompanyId(subCompanyId);
			}
			User result = userRepository.save(user);
			return new BaseResponse<>(200, "success", result);
		} else {
			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
		}
	}

	@Override
	public Object find(User user, Page page) {
		// TODO Auto-generated method stub
		// 查询条件设置默认值
		user.setDelTag(1);
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		// 创建实例
		Example<User> example = Example.of(user, matcher);
		Long total = userRepository.count(example);
		// 分页构造
		Pageable pageable = null;
		Object list = new ArrayList<>();
		if (page.getPageNo() != null && page.getPageSize() != null) {
			List<Sort.Order> orders = new ArrayList<>();
			orders.add(new Sort.Order(Sort.Direction.ASC, "showOrder"));
			Sort sort = Sort.by(orders);
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize(), sort);
			list = userRepository.findAll(example, pageable).getContent();
		} else {
			list = userRepository.findAll(example);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("data", list);
		result.put("pageNo", page.getPageNo());
		result.put("pageSize", page.getPageSize());
		result.put("totalCount", total);
		if (page.getPageNo() == null || page.getPageSize() == null) {
			result.put("totalPage", 1);
		} else {
			result.put("totalPage",
					total % page.getPageSize() == 0 ? total / page.getPageSize() : total / page.getPageSize() + 1);
		}
		return new BaseResponse<>(200, "success", result);
	}

	@Override
	public User getUserByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		Example<User> example = Example.of(user, matcher);
		List<User> list = userRepository.findAll(example);
		if (list != null && list.size() == 1) {
			return list.get(0);
		} else {
			return null;
		}

	}

	@Override
	public User getUserByToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = request.getHeader("token");
		String username = JwtUtils.getClaimFiled(token, "username");
		User user = userRepository.getUserByUsername(username);
		return user;
	}

	@Override
	public Object getUserGridList(Page page) {
		Integer pageNo = page.getPageNo();
		Integer pageSize = page.getPageSize();
		// TODO Auto-generated method stub

		String countSql = " select t1.*,t2.id as grid,t2.gridName from user t1  " + 
				" LEFT JOIN gridcommunity t2 on instr(t2.patrolManager,t1.id)  " + 
				" where t1.delTag=1 and t2.id is not null ";

		String dataSql = countSql;
		if (!StringUtils.isEmpty(pageNo) && !StringUtils.isEmpty(pageSize)) {
			Integer start = (pageNo - 1) * pageSize;
			Integer offset = pageSize;
			dataSql += " limit " + start + "," + offset;
		}
		dataSql += " order by t1.name ";
		// System.out.println("=========="+dataSql);
		int total = entityManager.createNativeQuery(countSql).getResultList().size();
		List<Map<String, Object>> resultList = entityManager.createNativeQuery(dataSql).unwrap(NativeQueryImpl.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
		Map<String, Object> result = new HashMap<>();
		result.put("data", resultList);
		result.put("pageNo", pageNo);
		result.put("pageSize", pageSize);
		result.put("totalCount", total);
		if (pageNo == null || pageSize == null) {
			result.put("totalPage", 1);
		} else {
			result.put("totalPage", total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
		}
		return new BaseResponse<>(200, "success", result);
	}

}
