package com.plat.sysconfig.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.sysconfig.entity.CompanyManage;

@Repository
public interface CompanyManageRepository extends JpaRepository<CompanyManage,String> {

	@Transactional
    @Modifying
	@Query(value = "update CompanyManage set delTag=0 where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);

	@Query(value = "select id from CompanyManage where companyName = ?1 ",nativeQuery = true )
	public String getCompanyIdByName(String companyName);
	
	@Query(value = "select count(1) from CompanyManage where deltag=1 ",nativeQuery = true )
	public Integer getTotal();
}
