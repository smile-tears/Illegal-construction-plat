package com.plat.sysconfig.web;
import com.plat.sysconfig.entity.SysGlobalConfig;
import com.plat.sysconfig.service.SysGlobalConfigService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysGlobalConfig")
public class SysGlobalConfigController {

    @Autowired
    private SysGlobalConfigService sysGlobalConfigService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody SysGlobalConfig sysGlobalConfig){
        return sysGlobalConfigService.save(sysGlobalConfig);
    }


	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody SysGlobalConfig sysGlobalConfig) {
		return sysGlobalConfigService.Update(sysGlobalConfig);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(SysGlobalConfig sysGlobalConfig ) {
		return sysGlobalConfigService.find(sysGlobalConfig );
	}
}
