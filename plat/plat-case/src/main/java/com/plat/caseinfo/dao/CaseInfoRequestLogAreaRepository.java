package com.plat.caseinfo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.caseinfo.entity.CaseInfoRequestLogArea;

@Repository
public interface CaseInfoRequestLogAreaRepository extends JpaRepository<CaseInfoRequestLogArea,String> {

	@Transactional
    @Modifying
	@Query(value = "update CaseInfoRequestLogArea set delTag=0 where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);

	@Query(value = "SELECT caseid from caseinfo_requestlog_area where caseid=?1 \r\n" + 
			"and operator=?2 and result is null ",nativeQuery = true)
	public String getRequestLogId (String caseid,String operator);
}