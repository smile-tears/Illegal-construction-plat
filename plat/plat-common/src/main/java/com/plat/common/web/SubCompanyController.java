package com.plat.common.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.plat.common.dao.SubCompanyRepository;
import com.plat.common.dao.UserRepository;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.common.entity.Department;
import com.plat.common.entity.SubCompany;
import com.plat.common.entity.User;
import com.plat.common.service.DepartmentService;
import com.plat.common.service.SubCompanyService;
import com.plat.common.service.UserService;
import com.plat.common.utils.JwtUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subCompany")
public class SubCompanyController {

	@Autowired
	private SubCompanyService subCompanyService;
	
	@Autowired
	UserRepository userRepository;

	/**
	 * 新增
	 */
	@PostMapping("/post")
	public Object save(@RequestBody SubCompany subCompany) {
		return subCompanyService.save(subCompany);
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public Object deleteById(@RequestBody String[] ids) {
		return subCompanyService.deleteByIds(ids);
	}

	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update(@RequestBody SubCompany subCompany) {
		return subCompanyService.Update(subCompany);
	}

	/**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(SubCompany subCompany, Page page) {
		return subCompanyService.find(subCompany, page);
	}
	
	/**
	 * 分部-部门树
	 */
	@PostMapping("/tree")
	public Object getTree(@RequestParam(value = "id", defaultValue = "0") String id, JSONArray jsonArray
			,HttpServletRequest request) {
		User currentUser = userService.getUserByToken(request);
		String subCompanyId = currentUser.getSubCompanyId();
		JSONArray result = new JSONArray();
		if ("admin".equals(currentUser.getUsername())) {
			result = recursionSubCompany(id, jsonArray,1,id);
		} else {
			result = recursionSubCompany(subCompanyId, jsonArray,1,subCompanyId);
		}
		JSONArray children = jsonArray.getJSONObject(0).getJSONArray("children");
		if (children.size() > 0 && "根目录".equals(result.getJSONObject(0).getString("title"))) {
			result = children;
		} 
		return new BaseResponse<>(200, "success", result);
	}

	/**
	 * 分部树
	 */
	@PostMapping("/tree2")
	public Object getTree2(@RequestParam(value = "id", defaultValue = "0") String id, JSONArray jsonArray
			,HttpServletRequest request) {
		User currentUser = userService.getUserByToken(request);
		String subCompanyId = currentUser.getSubCompanyId();
		JSONArray result = new JSONArray();
		if ("admin".equals(currentUser.getUsername())) {
			result = recursionSubCompany(id, jsonArray,2,id);
		} else {
			result = recursionSubCompany(subCompanyId, jsonArray,2,subCompanyId);
		}
		JSONArray children = result.getJSONObject(0).getJSONArray("children");
		if (children.size() > 0 && "根目录".equals(result.getJSONObject(0).getString("title"))) {
			result = children;
		} 
		return new BaseResponse<>(200, "success", result);
	}
	/**
	 * 分部-部门-人员树
	 */
	@Autowired
	SubCompanyRepository subCompanyRepository;
	
	@PostMapping("/user-tree")
	public Object getTree3(@RequestParam(value = "id", defaultValue = "0") String id, JSONArray jsonArray
			,HttpServletRequest request) {
		User currentUser = userService.getUserByToken(request);
		String subCompanyId = currentUser.getSubCompanyId();
		JSONArray result = new JSONArray();
		if ("admin".equals(currentUser.getUsername())) {
			result = recursionSubCompany( id, jsonArray,3,id);
		} else {
			result = recursionSubCompany( subCompanyId, jsonArray,3,subCompanyId);
		}
		
		JSONArray children = result.getJSONObject(0).getJSONArray("children");
		if (children.size() > 0 && "根目录".equals(result.getJSONObject(0).getString("title"))) {
			result = children;
		}
		
		return new BaseResponse<>(200, "success", result);
	}
	
	public JSONArray recursionSubCompany(String id, JSONArray jsonArray, int type,String compareId) {
		List<SubCompany> list = new ArrayList<SubCompany>();
		if (compareId.equals("0")) { //管理员
			list = subCompanyService.getChildrenSubCompanyId(id);
		} else {
			list.add(subCompanyRepository.getOne(compareId));
		}
		
		for (SubCompany subCompany : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("key", subCompany.getId());
			jsonObject.put("value", subCompany.getId());
			jsonObject.put("title", subCompany.getSubCompanyName());
			JSONArray jsonArray1 = new JSONArray();
			if ((type != 2 && id.equals(compareId)) || compareId.equals("0")) {
				jsonArray1 = recursionDepartment(subCompany.getId(),subCompany.getId(), new JSONArray(),type);
			}
			if (compareId.equals("0")) { // 只有管理员可以递归子分部
				JSONArray jsonArray2 = recursionSubCompany(subCompany.getId(), new JSONArray(),type,compareId);
				jsonArray1.addAll(jsonArray2);
			} else { // 非管理员只可以递归所在分部的一级分部
				List<SubCompany> list2  = subCompanyService.getChildrenSubCompanyId(subCompany.getId());
				for (SubCompany subCompany2 : list2) {
					JSONObject jsonObject2 = new JSONObject();
					jsonObject2.put("key", subCompany2.getId());
					jsonObject2.put("value", subCompany2.getId());
					jsonObject2.put("title", subCompany2.getSubCompanyName());
					jsonArray1.add(jsonObject2);
				}
			}
			
			jsonObject.put("children", jsonArray1);
			jsonObject.put("icon", subCompany.getIcon());
			jsonObject.put("type", "subCompany");
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;
	public JSONArray recursionDepartment(String id,String subCompanyId, JSONArray jsonArray, int type) {
		List<Department> list = departmentService.getSubDepartmentById(id,subCompanyId);
		for (Department department : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("key", department.getId());
			jsonObject.put("value", department.getId());
			jsonObject.put("title", department.getDepartmentName());
			JSONArray jsonArray1 = new JSONArray();
			if (type == 3) {
				User user = new User();
				user.setDepartmentId(department.getId());
				JSONObject object = (JSONObject)JSONObject.toJSON(userService.find(user, new Page()));
				JSONArray data = object.getJSONObject("result").getJSONArray("data");
				for (int i=0; i< data.size();i++) {
					JSONObject userTemp = data.getJSONObject(i);
					JSONObject jSONObject1 = new JSONObject();
					jSONObject1.put("key", userTemp.get("id"));
					jSONObject1.put("value", userTemp.get("id"));
					jSONObject1.put("title", userTemp.get("name"));
					jSONObject1.put("type", "user");
					jsonArray1.add(jSONObject1);
				}
				
			}
			JSONArray jsonArray2 = recursionDepartment(department.getId(),subCompanyId, new JSONArray(),type);
			jsonArray1.addAll(jsonArray2);
			jsonObject.put("children", jsonArray1);
			jsonObject.put("icon", department.getIcon());
			jsonObject.put("type", "department");
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
}
