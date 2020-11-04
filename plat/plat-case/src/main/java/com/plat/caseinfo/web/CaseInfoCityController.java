package com.plat.caseinfo.web;
import com.plat.common.entity.Page;
import com.plat.caseinfo.Const;
import com.plat.caseinfo.entity.CaseInfoCity;
import com.plat.caseinfo.service.CaseInfoCityService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caseInfoCity")
public class CaseInfoCityController {

    @Autowired
    private CaseInfoCityService caseInfoCityService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody CaseInfoCity caseInfoCity){
        return caseInfoCityService.save(caseInfoCity);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return caseInfoCityService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody CaseInfoCity caseInfoCity) {
		return caseInfoCityService.Update(caseInfoCity);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(CaseInfoCity caseInfoCity, Page page) {
		return caseInfoCityService.find(caseInfoCity,page);
	}
	
	/**
	 * 查询
	 */
	@PostMapping("/find2")
	public Object find(CaseInfoCity caseInfoCity, Page page,HttpServletRequest request) {
		return caseInfoCityService.find2(null,caseInfoCity, page, request);
	}
	
	@PostMapping("/type")
	public String getType() {
		return Const.type;
	}
}
