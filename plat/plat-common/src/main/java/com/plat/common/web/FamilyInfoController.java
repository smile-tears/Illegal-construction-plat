package com.plat.common.web;
import com.plat.common.entity.Page;
import com.plat.common.entity.FamilyInfo;
import com.plat.common.service.FamilyInfoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/familyInfo")
public class FamilyInfoController {

    @Autowired
    private FamilyInfoService familyInfoService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody FamilyInfo familyInfo){
        return familyInfoService.save(familyInfo);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return familyInfoService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody FamilyInfo familyInfo) {
		return familyInfoService.Update(familyInfo);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(FamilyInfo familyInfo, Page page) {
		return familyInfoService.find(familyInfo,page);
	}
}
