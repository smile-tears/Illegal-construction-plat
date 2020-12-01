package com.plat.caseinfo.dao;


import java.util.List;
import java.util.Map;

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

	@Query(value = "select t2.*,t3.name,t3.telephone as mobile from ( " + 
			" SELECT userid,max(uploadTime) uploadTime from userposition GROUP BY userid)t1 " + 
			" LEFT JOIN userposition t2 on t1.userid=t2.userid and t1.uploadTime=t2.uploadTime " + 
			" LEFT JOIN user t3 on t1.userid=t3.id " + 
			" where  SUBSTR(t1.uploadTime,1,10)=DATE_FORMAT(NOW(),'%Y-%m-%d')",nativeQuery = true )
	public List<Map<String, String>> getRecentPosition() ;
}
