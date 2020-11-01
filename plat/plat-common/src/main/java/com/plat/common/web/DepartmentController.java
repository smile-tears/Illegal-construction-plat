package com.plat.common.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.entity.Department;
import com.plat.common.service.DepartmentService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	/**
	 * 新增
	 */
	@PostMapping("/post")
	public Object save(@RequestBody Department department) {
		return departmentService.save(department);
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public Object deleteById(@RequestBody String[] ids) {
		return departmentService.deleteByIds(ids);
	}

	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update(@RequestBody Department department) {
		return departmentService.Update(department);
	}

	/**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(Department department, Page page) {
		return departmentService.find(department, page);
	}

	/**
	 * 获取部门树
	 */
	@PostMapping("/tree")
	public Object getTree(@RequestParam(value = "id", defaultValue = "0") String id, 
			String subCompanyId, JSONArray jsonArray) {
		return new BaseResponse<>(200, "success", recursion(id,subCompanyId, jsonArray));
	}
	
	public JSONArray recursion(String id,String subCompanyId, JSONArray jsonArray) {
		List<Department> list = departmentService.getSubDepartmentById(id,subCompanyId);
		for (Department department : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("key", department.getId());
			jsonObject.put("value", department.getId());
			jsonObject.put("title", department.getDepartmentName());
			jsonObject.put("children", recursion(department.getId(),subCompanyId, new JSONArray()));
			jsonObject.put("icon", department.getIcon());
			jsonObject.put("type", "department");
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
}
