package com.plat.caseinfo.dao;


import java.util.List;
import java.util.Map;

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

	@Query(value = "select status,count(1) num from CaseInfo_City "
			+" where SUBSTR(reportTime,1,10)=DATE_FORMAT(NOW(),'%Y-%m-%d') group by status ",nativeQuery = true)
	public List<Map<String, Object>> report();
}
