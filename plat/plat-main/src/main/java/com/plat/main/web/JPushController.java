package com.plat.main.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.plat.common.dao.UserRepository;
import com.plat.main.config.JPushConstants;
import com.plat.main.service.JPushClientService;

@RestController
public class JPushController {
	
	@Autowired
	JPushClientService jPushClientService;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping(value = "/jPush/{userId}")
	public Object JPushTest(@PathVariable String userId) {
		String registrationID = userRepository.getOne(userId).getRegistrationID();
		List<String> audienceValues = new ArrayList<String>();
		audienceValues.add(registrationID);
		int code = jPushClientService.sendPush(JPushConstants.PLATFORM_ANDROID, JPushConstants.AUDIENCE_REGISTRATION_ID, 
				audienceValues, "指挥中心请求通话", "视频通话", "视频通话");
		return code;
	}
}
