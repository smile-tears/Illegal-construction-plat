package com.plat.caseinfo.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.plat.common.config.JPushConstants;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.service.JPushClientService;
import com.plat.common.utils.BeanProcessUtils;
import com.plat.common.utils.StringUtil;
import com.plat.common.utils.TimeUtil;
import com.plat.caseinfo.dao.MessageReceiveRepository;
import com.plat.caseinfo.dao.MessageRepository;
import com.plat.caseinfo.entity.Message;
import com.plat.caseinfo.entity.MessageReceive;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	MessageReceiveRepository messageReceiveRepository;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	JPushClientService jPushClientService;
	
//	@PersistenceContext 
//    EntityManager entityManager;
	
	@Override
	public Object save(Message message) {
		// TODO Auto-generated method stub
		List<MessageReceive> messageReceiveList = message.getMessageReceiveList();
		
		//保存主表 清除message 中的 messageReceiveList
        message.setMessageReceiveList(null);
        message.setSendTime(TimeUtil.getNowTime());
        Message result = messageRepository.save(message);
        
		//保存明细表 批量插入消息接收明细
		String sql = "Insert into MessageReceive(id,userid,status,messageId) values(?,?,?,?)";
        jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,StringUtil.getUUID());
                ps.setString(2,messageReceiveList.get(i).getUser().getId());
                ps.setInt(3,messageReceiveList.get(i).getStatus());
                ps.setString(4,result.getId());
            }
            @Override
            public int getBatchSize() {
                return messageReceiveList.size();
            }
        });
        
        // 发送手机通知
        List<String > audienceValues = messageRepository.audienceValues(result.getId());
        if (audienceValues.size() > 0) {
        	jPushClientService.sendPush(JPushConstants.PLATFORM_ANDROID, JPushConstants.AUDIENCE_REGISTRATION_ID, 
    				audienceValues, result.getTitle(), "通知", result.getId());
        }
		
		return new BaseResponse<>(200, "success");
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		messageRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

 
	@Override
	public Object find(Message message, Page page,String startDate,String endDate) {
		// TODO Auto-generated method stub
		// 排序
		List<Sort.Order> orders = new ArrayList<>();
		orders.add(new Sort.Order(Sort.Direction.DESC,"sendTime")); 
		Sort sort = Sort.by(orders);
		//分页
		Pageable pageable = null;
		if (!StringUtils.isEmpty(page.getPageNo()) && !StringUtils.isEmpty(page.getPageSize())) {
			pageable = PageRequest.of(page.getPageNo() - 1, page.getPageSize(),sort); // pageIndex默认从0开始
		} else {
			return new BaseResponse<>(500, "请传入请求分页参数！");
		}
		Specification<Message> specification = new Specification<Message>() {
			@Override
			public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate = cb.conjunction();
				if (!StringUtils.isEmpty(message.getTitle())) {
					predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+message.getTitle()+"%"));
				}
				if (!StringUtils.isEmpty(startDate)) {
					predicate.getExpressions().add(cb.greaterThan(root.get("sendTime").as(String.class), startDate+" 00:00:01"));
				}
				if (!StringUtils.isEmpty(endDate)) {
					predicate.getExpressions().add(cb.lessThan(root.get("sendTime").as(String.class), endDate+" 23:59:59"));
				}
				return predicate;
			}
		};
		long total = messageRepository.count(specification);
		List<Message> data = messageRepository.findAll(specification,pageable).getContent();
		Map<String, Object> result = new HashMap<>();
		result.put("data", data);
		result.put("pageNo", page.getPageNo());
		result.put("totalCount", total);
		if (page.getPageNo() != null && page.getPageSize() !=null ) {
			result.put("totalPage", total % page.getPageSize() == 0 ? total / page.getPageSize() : total / page.getPageSize() + 1);
		}
		return new BaseResponse<>(200, "success", result);

	}

	 
}
