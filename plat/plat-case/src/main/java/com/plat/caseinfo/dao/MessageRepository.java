package com.plat.caseinfo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.caseinfo.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, String>, JpaSpecificationExecutor<Message> {

	@Transactional
	@Modifying
	@Query(value = "update Message set delTag=0 where id in ?1 ", nativeQuery = true)
	public void deleteByIds(String[] ids);

	@Override
	@EntityGraph("Message.MessageReceive")
	Page<Message> findAll(Specification<Message> spec, Pageable pageable) ;
	
	
}
