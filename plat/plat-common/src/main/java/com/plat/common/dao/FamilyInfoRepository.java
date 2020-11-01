package com.plat.common.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.plat.common.entity.FamilyInfo;

@Repository
public interface FamilyInfoRepository extends JpaRepository<FamilyInfo,String> {

	@Transactional
    @Modifying
	@Query(value = "delete from FamilyInfo where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);
}
