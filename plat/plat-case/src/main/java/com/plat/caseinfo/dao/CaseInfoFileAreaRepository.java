package com.plat.caseinfo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.caseinfo.entity.CaseInfoFileArea;

@Repository
public interface CaseInfoFileAreaRepository extends JpaRepository<CaseInfoFileArea,String> {

	@Transactional
    @Modifying
	@Query(value = "update CaseInfoFileArea set delTag=0 where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);

}
