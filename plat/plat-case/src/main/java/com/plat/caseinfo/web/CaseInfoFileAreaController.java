package com.plat.caseinfo.web;
import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.CaseInfoFileArea;
import com.plat.caseinfo.service.CaseInfoFileAreaService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caseInfoFileArea")
public class CaseInfoFileAreaController {

    @Autowired
    private CaseInfoFileAreaService caseInfoFileAreaService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody CaseInfoFileArea caseInfoFileArea){
        return caseInfoFileAreaService.save(caseInfoFileArea);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return caseInfoFileAreaService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody CaseInfoFileArea caseInfoFileArea) {
		return caseInfoFileAreaService.Update(caseInfoFileArea);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(CaseInfoFileArea caseInfoFileArea, Page page) {
		return caseInfoFileAreaService.find(caseInfoFileArea,page);
	}
}
