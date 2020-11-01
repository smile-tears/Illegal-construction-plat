package com.plat.caseinfo.web;
import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.CaseInfoRequestLogStreet;
import com.plat.caseinfo.service.CaseInfoRequestLogStreetService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caseInfoRequestLogStreet")
public class CaseInfoRequestLogStreetController {

    @Autowired
    private CaseInfoRequestLogStreetService caseInfoRequestLogStreetService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody CaseInfoRequestLogStreet caseInfoRequestLogStreet,HttpServletRequest request){
        return caseInfoRequestLogStreetService.save(caseInfoRequestLogStreet,request);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return caseInfoRequestLogStreetService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody CaseInfoRequestLogStreet caseInfoRequestLogStreet) {
		return caseInfoRequestLogStreetService.Update(caseInfoRequestLogStreet);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(CaseInfoRequestLogStreet caseInfoRequestLogStreet, Page page) {
		return caseInfoRequestLogStreetService.find(caseInfoRequestLogStreet,page);
	}
}
