package com.plat.caseinfo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.caseinfo.entity.UserPosition;

@Repository
public interface UserPositionRepository extends JpaRepository<UserPosition,String> {

	@Transactional
    @Modifying
	@Query(value = "delete from UserPosition where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);

}
