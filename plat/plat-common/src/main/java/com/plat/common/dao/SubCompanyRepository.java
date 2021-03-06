package com.plat.common.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.common.entity.Department;
import com.plat.common.entity.SubCompany;

@Repository
public interface SubCompanyRepository extends JpaRepository<SubCompany,String> {

	@Transactional
    @Modifying
	@Query(value = "update SubCompany set delTag=0 where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);

	@Query(value = "select * from SubCompany where delTag=1 and supSubCompanyId=?1 order by showorder",nativeQuery = true )
	public List<SubCompany> getChildrenSubCompanyId(String id);
}
