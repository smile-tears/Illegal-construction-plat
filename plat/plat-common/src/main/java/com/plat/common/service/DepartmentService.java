package com.plat.common.service;

import java.util.List;

import com.plat.common.entity.Page;
import com.plat.common.entity.Department;

public interface DepartmentService {
	public Object save(Department department);

	public Object deleteByIds(String[] ids);

	public Object Update(Department department);

	public Object find(Department department, Page page);
	
	public List<Department> getSubDepartmentById(String id,String subCompanyId);
}