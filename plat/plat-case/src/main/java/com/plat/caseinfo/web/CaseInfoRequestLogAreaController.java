package com.plat.caseinfo.web;
import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.CaseInfoRequestLogArea;
import com.plat.caseinfo.service.CaseInfoRequestLogAreaService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caseInfoRequestLogArea")
public class CaseInfoRequestLogAreaController {

    @Autowired
    private CaseInfoRequestLogAreaService caseInfoRequestLogAreaService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody CaseInfoRequestLogArea caseInfoRequestLogArea,HttpServletRequest request){
        return caseInfoRequestLogAreaService.save(caseInfoRequestLogArea,request);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return caseInfoRequestLogAreaService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody CaseInfoRequestLogArea caseInfoRequestLogArea) {
		return caseInfoRequestLogAreaService.Update(caseInfoRequestLogArea);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(CaseInfoRequestLogArea caseInfoRequestLogArea, Page page) {
		return caseInfoRequestLogAreaService.find(caseInfoRequestLogArea,page);
	}
}
