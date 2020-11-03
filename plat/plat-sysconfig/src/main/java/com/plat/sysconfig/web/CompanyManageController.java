package com.plat.sysconfig.web;
import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.CompanyManage;
import com.plat.sysconfig.service.CompanyManageService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/companyManage")
public class CompanyManageController {

    @Autowired
    private CompanyManageService companyManageService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody CompanyManage companyManage){
        return companyManageService.save(companyManage);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return companyManageService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody CompanyManage companyManage) {
		return companyManageService.Update(companyManage);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(CompanyManage companyManage, Page page) {
		return companyManageService.find(companyManage,page);
	}
}
