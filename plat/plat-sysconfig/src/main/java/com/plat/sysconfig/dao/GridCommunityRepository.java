package com.plat.sysconfig.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.sysconfig.entity.GridCommunity;

import java.util.List;
import java.util.Map;

@Repository
public interface GridCommunityRepository extends JpaRepository<GridCommunity,String> {

	@Transactional
    @Modifying
	@Query(value = "update GridCommunity set delTag=0 where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);


	@Query(value = "select distinct id,subCompanyName from  subcompany where delTag <>0 " +
			" union all " +
			" select distinct id,departmentName from department ",nativeQuery = true)
	public List<Map<String,String>> getIdAndName();

	@Query(value = " select id from department where departmentName like  CONCAT('%',?1,'%') " +
			" union all " +
			" select id from  subcompany where subCompanyName like CONCAT('%',?1,'%') ",nativeQuery = true)
	public List<String> getDepIds(@Param("depname")String depname);


	@Query(value = " select id from user where name like  CONCAT('%',?1,'%') ",nativeQuery = true)
	public List<String> getUserIds(@Param("username")String username);

	@Query(value = "select id,name from user",nativeQuery = true)
	public List<Map<String,String>> getUserIdAndName();

	@Query(value = "select count(1) as count from gridcommunity where gridName = ?1 and delTag=1 " +
			" and IF (?2 != '', id != ?2, 1=1) ",nativeQuery = true)
	public int checkGridName(String name,String id);

	@Query(value = "select telephone from user where id= ?1",nativeQuery = true)
	public String getTelephone(String id);

}
