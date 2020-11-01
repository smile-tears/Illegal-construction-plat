package com.plat.caseinfo.web;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.caseinfo.dao.CaseInfoFileCityRepository;
import com.plat.caseinfo.entity.CaseInfoFileCity;
import com.plat.caseinfo.service.CaseInfoFileCityService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caseInfoFileCity")
public class CaseInfoFileCityController {

    @Autowired
    private CaseInfoFileCityService caseInfoFileCityService;

    @Autowired
	CaseInfoFileCityRepository caseInfoFileCityRepository;
    
    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody CaseInfoFileCity caseInfoFileCity){
        return caseInfoFileCityService.save(caseInfoFileCity);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(String id){
    	caseInfoFileCityRepository.deleteById(id);
    	return new BaseResponse<>(200, "删除成功");
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody CaseInfoFileCity caseInfoFileCity) {
		return caseInfoFileCityService.Update(caseInfoFileCity);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(CaseInfoFileCity caseInfoFileCity, Page page) {
		return caseInfoFileCityService.find(caseInfoFileCity,page);
	}
}
