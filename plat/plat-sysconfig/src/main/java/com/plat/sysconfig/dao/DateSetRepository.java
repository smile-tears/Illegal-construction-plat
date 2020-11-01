package com.plat.sysconfig.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.sysconfig.entity.DateSet;

import java.util.List;

@Repository
public interface DateSetRepository extends JpaRepository<DateSet,String> {

	@Transactional
    @Modifying
	@Query(value = "update DateSet set delTag=0 where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);

	public List<DateSet> findByDelTag(int delTag);

}
