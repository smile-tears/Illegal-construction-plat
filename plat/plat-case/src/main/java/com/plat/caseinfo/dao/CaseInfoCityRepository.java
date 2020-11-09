package com.plat.caseinfo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.caseinfo.entity.CaseInfoCity;

@Repository
public interface CaseInfoCityRepository extends JpaRepository<CaseInfoCity,String> {

	@Transactional
    @Modifying
	@Query(value = "delete from CaseInfo_City where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);

	@Query(value = "CALL generate_orderNo(?1, ?2, @orderNo); ",nativeQuery = true)
	public String getCaseFlowCode(String alpha,int type);
}
