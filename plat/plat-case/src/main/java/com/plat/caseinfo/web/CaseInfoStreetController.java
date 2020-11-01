package com.plat.caseinfo.web;
import com.plat.common.entity.Page;
import com.plat.caseinfo.Const;
import com.plat.caseinfo.entity.CaseInfoStreet;
import com.plat.caseinfo.service.CaseInfoStreetService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caseInfoStreet")
public class CaseInfoStreetController {

    @Autowired
    private CaseInfoStreetService caseInfoStreetService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody CaseInfoStreet caseInfoStreet){
        return caseInfoStreetService.save(caseInfoStreet);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return caseInfoStreetService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody CaseInfoStreet caseInfoStreet) {
		return caseInfoStreetService.Update(caseInfoStreet);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(CaseInfoStreet caseInfoStreet, Page page) {
		return caseInfoStreetService.find(caseInfoStreet,page);
	}
	
	/**
	 * 查询
	 */
	@PostMapping("/find2")
	public Object find(int status, Page page,String operator) {
		return caseInfoStreetService.find2(status, page, operator);
	}
	
	@PostMapping("/type")
	public String getType() {
		return Const.type;
	}
}
