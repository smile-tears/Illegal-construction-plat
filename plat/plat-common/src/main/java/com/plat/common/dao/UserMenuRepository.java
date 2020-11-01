package com.plat.common.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.common.entity.UserMenu;

@Repository
public interface UserMenuRepository extends JpaRepository<UserMenu,String> {

	@Transactional
    @Modifying
	@Query(value = "delete from User_Menu where userid=?1 ",nativeQuery = true )
	public void deleteByUserId(String userId);

	@Query(value = "select t1.menuId from user_menu t1 , menu t2 " + 
			"where t1.menuId=t2.id and t1.userId=?1 " + 
			"and not EXISTS (SELECT 1 from menu where supMenuid=t1.menuid ) ",nativeQuery = true )
	public List<String> getMenuIdsByUserId (String userId);
}
