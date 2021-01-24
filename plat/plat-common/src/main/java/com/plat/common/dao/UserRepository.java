package com.plat.common.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.common.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @description 人员
 * @author admin
 * @date 2020-05-02 20:58:17
 */
@Repository
public interface UserRepository extends JpaRepository<User,String> {

	@Transactional
    @Modifying
	@Query(value = "update User set delTag=0 where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[]  ids);
	
	@Query(value = "select * from user where username=?1",nativeQuery = true)
	public User getUserByUsername(String username);
	
	@Query(value = "select * from user where name=?1",nativeQuery = true)
	public User getUserByName(String name);
	
	@Transactional
    @Modifying
	@Query(value = "update User set RegistrationID=?1 where id = ?2 ",nativeQuery = true )
	public Integer updateRegistrationID(String registrationID, String id);
	
}
