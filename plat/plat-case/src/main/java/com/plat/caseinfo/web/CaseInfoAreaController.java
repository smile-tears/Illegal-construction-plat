package com.plat.caseinfo.web;
import com.plat.common.entity.Page;
import com.plat.caseinfo.Const;
import com.plat.caseinfo.entity.CaseInfoArea;
import com.plat.caseinfo.service.CaseInfoAreaService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caseInfoArea")
public class CaseInfoAreaController {

    @Autowired
    private CaseInfoAreaService caseInfoAreaService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody CaseInfoArea caseInfoArea){
        return caseInfoAreaService.save(caseInfoArea);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return caseInfoAreaService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody CaseInfoArea caseInfoArea) {
		return caseInfoAreaService.Update(caseInfoArea);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(CaseInfoArea caseInfoArea, Page page) {
		return caseInfoAreaService.find(caseInfoArea,page);
	}
	
	/**
	 * 查询
	 */
	@PostMapping("/find2")
	public Object find(int status, Page page,String operator) {
		return caseInfoAreaService.find2(status, page, operator);
	}
	
	@PostMapping("/type")
	public String getType() {
		return Const.type;
	}
}
