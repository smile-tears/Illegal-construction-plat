package com.plat.common.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.common.entity.WorkInfo;

@Repository
public interface WorkInfoRepository extends JpaRepository<WorkInfo,String> {

	@Transactional
    @Modifying
	@Query(value = "delete from WorkInfo where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);

}
