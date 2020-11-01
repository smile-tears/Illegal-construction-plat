package com.plat.sysconfig.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.sysconfig.entity.Sample;

@Repository
public interface SampleRepository extends JpaRepository<Sample,String> {

	@Transactional
    @Modifying
	@Query(value = "update Sample set delTag=0 where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);

}
