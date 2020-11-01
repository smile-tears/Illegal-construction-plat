package com.plat.main.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.plat.main.config.JPushConstants;
import com.plat.main.service.JPushClientService;

@RestController
public class JPushController {
	
	@Autowired
	JPushClientService jPushClientService;
	
	@GetMapping(value = "/jPush/{registrationID}")
	public Object JPushTest(@PathVariable String registrationID) {
		List<String> audienceValues = new ArrayList<String>();
		audienceValues.add(registrationID);
		int code = jPushClientService.sendPush(JPushConstants.PLATFORM_ANDROID, JPushConstants.AUDIENCE_REGISTRATION_ID, 
				audienceValues, "测试标题", "测试内容", "测试别名");
		return code;
	}
}
