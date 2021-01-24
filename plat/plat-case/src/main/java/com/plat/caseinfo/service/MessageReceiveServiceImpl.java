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
import com.plat.caseinfo.dao.MessageReceiveRepository;
import com.plat.caseinfo.entity.MessageReceive;
import com.plat.caseinfo.entity.UserPosition;

@Service
public class MessageReceiveServiceImpl implements MessageReceiveService {

	@Autowired
	MessageReceiveRepository messageReceiveRepository;
	
	@PersistenceContext 
    EntityManager entityManager;

	@Override
	public Object save(MessageReceive messageReceive) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", messageReceiveRepository.save(messageReceive));
	}

	@Override
	public Object deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		messageReceiveRepository.deleteByIds(ids);
		return new BaseResponse<>(200, "删除成功");
	}

 

	@Override
	public Object find(MessageReceive messageReceive, Page page) {
		// TODO Auto-generated method stub
		Integer pageNo = page.getPageNo();
		Integer pageSize = page.getPageSize();
		String countSql = " SELECT t1.*,t2.content,t2.sendTime,t2.title,t2.remark,t2.fileName,t2.filePath "
				+" from messagereceive t1 "
				+" JOIN message t2 on t1.messageid=t2.id where 1=1   ";
		if (!StringUtils.isEmpty(messageReceive.getUser().getId())) {
			countSql += " and t1.userid='"+messageReceive.getUser().getId()+"'";
		}
		if (!StringUtils.isEmpty(messageReceive.getStatus())) {
			countSql += " and t1.status='"+messageReceive.getStatus()+"'";
		}
		countSql += " order by t2.sendTime desc";
		String dataSql = countSql;
		if (!StringUtils.isEmpty(pageNo) && !StringUtils.isEmpty(pageSize)) {
			Integer start = (pageNo - 1 ) * pageSize;
			Integer offset = pageSize;
			dataSql += " limit " + start + "," + offset;
		}
		// System.out.println("=========="+dataSql);
		int total = entityManager.createNativeQuery(countSql).getResultList().size();

		List<Map<String, Object>> data = entityManager.createNativeQuery(dataSql)
				.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
		Map<String, Object> result = new HashMap<>();
		result.put("data", data);
		result.put("pageNo", page.getPageNo());
		result.put("totalCount", total);
		if (page.getPageNo() != null && page.getPageSize() !=null ) {
			result.put("totalPage", total % page.getPageSize() == 0 ? total / page.getPageSize() : total / page.getPageSize() + 1);
		}
		return new BaseResponse<>(200, "success", result);
	}

	@Override
	public Object read(String messageId,String userid) {
		// TODO Auto-generated method stub
		Integer count = messageReceiveRepository.read(messageId,userid);
		if (count > 0) {
			MessageReceive messageReceive = messageReceiveRepository.getByMessageIdAndUserid(messageId, userid);
			return new BaseResponse<>(200, "更新阅读状态成功",messageReceive);
		} else {
			return new BaseResponse<>(500, "更新阅读状态失败");
		}
		
	}

//	@Override
//	public Object Update(MessageReceive target) {
//		// TODO Auto-generated method stub
//		Optional<MessageReceive> source = messageReceiveRepository.findById(target.getId());
//		if (source.isPresent()) {
//			MessageReceive messageReceive = (MessageReceive) BeanProcessUtils.copy(source.get(), target);
//			MessageReceive result = messageReceiveRepository.save(messageReceive);
//			return new BaseResponse<>(200, "success", result);
//		} else {
//			return new BaseResponse<>(500, "id：" + target.getId() + "对应的数据未查到或已删除！");
//		}
//	}

}
