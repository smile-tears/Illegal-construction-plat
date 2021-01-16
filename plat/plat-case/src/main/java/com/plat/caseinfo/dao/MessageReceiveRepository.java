package com.plat.caseinfo.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.caseinfo.entity.MessageReceive;

@Repository
public interface MessageReceiveRepository extends JpaRepository<MessageReceive,String> {

	@Transactional
    @Modifying
	@Query(value = "update MessageReceive set delTag=0 where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);
	
	@Transactional
    @Modifying
	@Query(value = "update MessageReceive set status=0 where id = ?1 ",nativeQuery = true )
	public void read(String id);
	
	@Query(value = "select t2.registrationID from messageReceive t1, user t2 where t1.userid=t2.id "
			+" and t2.registrationID is not null "
			+" and t1.messageId = ?1 ", nativeQuery = true)
	public List<String> audienceValues(String messageId);
	
	@Query(value = "select t2.telephone from messageReceive t1, user t2 where t1.userid=t2.id "
			+" and t2.telephone is not null "
			+" and t1.messageId = ?1 ", nativeQuery = true)
	public List<String> getMobiles (String messageId);

}
