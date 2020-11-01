package com.plat.caseinfo.web;
import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.CaseInfoRequestLogCity;
import com.plat.caseinfo.service.CaseInfoRequestLogCityService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caseInfoRequestLogCity")
public class CaseInfoRequestLogCityController {

    @Autowired
    private CaseInfoRequestLogCityService caseInfoRequestLogCityService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody CaseInfoRequestLogCity caseInfoRequestLogCity,HttpServletRequest request){
        return caseInfoRequestLogCityService.save(caseInfoRequestLogCity,request);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return caseInfoRequestLogCityService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody CaseInfoRequestLogCity caseInfoRequestLogCity) {
		return caseInfoRequestLogCityService.Update(caseInfoRequestLogCity);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(CaseInfoRequestLogCity caseInfoRequestLogCity, Page page) {
		return caseInfoRequestLogCityService.find(caseInfoRequestLogCity,page);
	}
}
