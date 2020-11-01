package com.plat.common.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.common.entity.RoleMenu;

@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu,String> {

	@Transactional
    @Modifying
	@Query(value = "delete from Role_Menu where roleId=?1 ",nativeQuery = true )
	public void deleteByRoleId (String roleId);

	@Query(value = "select t1.menuId from role_menu t1 , menu t2 " + 
			"where t1.menuId=t2.id and roleid=?1 " + 
			"and not EXISTS (SELECT 1 from menu where supMenuid=t1.menuid ) ",nativeQuery = true )
	public List<String> getMenuIdsByRoleId (String roleId);
}
