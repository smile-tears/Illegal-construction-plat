package com.plat.caseinfo.web;
import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.CaseInfoFileStreet;
import com.plat.caseinfo.service.CaseInfoFileStreetService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caseInfoFileStreet")
public class CaseInfoFileStreetController {

    @Autowired
    private CaseInfoFileStreetService caseInfoFileStreetService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody CaseInfoFileStreet caseInfoFileStreet){
        return caseInfoFileStreetService.save(caseInfoFileStreet);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return caseInfoFileStreetService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody CaseInfoFileStreet caseInfoFileStreet) {
		return caseInfoFileStreetService.Update(caseInfoFileStreet);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(CaseInfoFileStreet caseInfoFileStreet, Page page) {
		return caseInfoFileStreetService.find(caseInfoFileStreet,page);
	}
}
