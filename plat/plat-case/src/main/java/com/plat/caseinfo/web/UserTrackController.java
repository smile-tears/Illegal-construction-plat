package com.plat.caseinfo.web;
import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.UserTrack;
import com.plat.caseinfo.service.UserTrackService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/userTrack")
public class UserTrackController {

    @Autowired
    private UserTrackService userTrackService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody UserTrack userTrack){
        return userTrackService.save(userTrack);
    }
    
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(String userid,String starttime,String endtime, Page page) {
		return userTrackService.find(userid,starttime,endtime,page);
	}
}
