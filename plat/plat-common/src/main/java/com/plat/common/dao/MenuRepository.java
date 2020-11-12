package com.plat.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.common.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu,String> {

	@Transactional
    @Modifying
	@Query(value = "update Menu set delTag=0 where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);

	@Query(value = "SELECT DISTINCT t2.* FROM ( " + 
			"select menuid from user_menu where userId=?1 " + 
			"union ALL " + 
			"SELECT menuid from role_menu t1 LEFT JOIN role t2 on t1.roleId=t2.id LEFT JOIN user_role t3 on t3.roleId=t1.roleId " + 
			"where t3.userId=?1 " +
			"union all "+
			"select id from menu m where m.supMenuid=?2 and not exists(select 1 from user_menu where menuid=m.id) " +
			"and not exists(select 1 from role_menu where menuId=m.id)"+
			") t1 LEFT JOIN menu t2 on t1.menuid=t2.id " + 
			"WHERE delTag=1 and visible=0 and t2.supMenuid=?2 order by showorder",nativeQuery = true)
	public List<Menu> getUserMenusBySupMenuId (String userId,String supMenuid);
	
	
	@Query(value = "SELECT * from menu WHERE delTag=1 and visible=0 and supMenuid=?1 order by showorder",nativeQuery = true)
	public List<Menu> getAdminMenusBySupMenuId (String supMenuid);
}
