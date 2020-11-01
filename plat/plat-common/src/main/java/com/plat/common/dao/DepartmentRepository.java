package com.plat.common.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plat.common.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,String> {

	@Transactional
    @Modifying
	@Query(value = "update Department set delTag=0 where id in ?1 ",nativeQuery = true )
	public void deleteByIds(String[] ids);

	@Query(value = "select * from Department where delTag=1 and supDeptId=?1 order by showorder",nativeQuery = true )
	public List<Department> getSubDepartmentById(String id);
	@Query(value = "select * from Department where delTag=1 and supDeptId=?1 and subCompanyId=?2 order by showorder",nativeQuery = true )
	public List<Department> getSubDepartmentById(String id,String subCompanyId);


}
