package com.plat.sysconfig.web;
import com.alibaba.fastjson.JSONObject;
import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.DateSet;
import com.plat.sysconfig.service.DateSetService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dateSet")
public class DateSetController {

    @Autowired
    private DateSetService dateSetService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody DateSet dateSet){
        return dateSetService.save(dateSet);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return dateSetService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody DateSet dateSet) {
		return dateSetService.Update(dateSet);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(DateSet dateSet, Page page) {
		return dateSetService.find(dateSet,page);
	}
}
