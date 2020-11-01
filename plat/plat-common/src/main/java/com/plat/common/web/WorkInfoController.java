package com.plat.common.web;
import com.plat.common.entity.Page;
import com.plat.common.entity.WorkInfo;
import com.plat.common.service.WorkInfoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workInfo")
public class WorkInfoController {

    @Autowired
    private WorkInfoService workInfoService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody WorkInfo workInfo){
        return workInfoService.save(workInfo);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return workInfoService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody WorkInfo workInfo) {
		return workInfoService.Update(workInfo);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(WorkInfo workInfo, Page page) {
		return workInfoService.find(workInfo,page);
	}
}
