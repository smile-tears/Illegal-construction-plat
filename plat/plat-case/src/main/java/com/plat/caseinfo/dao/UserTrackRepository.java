package com.plat.caseinfo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.caseinfo.entity.UserTrack;

@Repository
public interface UserTrackRepository extends JpaRepository<UserTrack,String> {

	@Transactional
    @Modifying
	@Query(value = "update UserTrack set delTag=0 where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);

}
