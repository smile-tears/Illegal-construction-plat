package com.plat.sysconfig.dao;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.sysconfig.entity.QuestionType;

@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType,String> {

	@Transactional
    @Modifying
	@Query(value = "update QuestionType set delTag=0 where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);

	@Query(value = "select * from QuestionType where fstLvlId is null order by showOrder ",nativeQuery = true )
	public List<Map<String, String>> getFstLvlType();
	
	@Query(value = "select * from QuestionType where fstLvlId in ?1 order by showOrder",nativeQuery = true )
	public List<Map<String, String>> getSecLvlType(String[] fstLvlIds);
}
