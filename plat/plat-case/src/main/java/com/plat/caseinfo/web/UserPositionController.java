package com.plat.caseinfo.web;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.UserPosition;
import com.plat.caseinfo.service.UserPositionService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/userPosition")
public class UserPositionController {

    @Autowired
    private UserPositionService userPositionService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody UserPosition userPosition){
        return userPositionService.save(userPosition);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return userPositionService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody UserPosition userPosition) {
		return userPositionService.Update(userPosition);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(String userid,String startTime,String endTime, Page page) {
		return userPositionService.find(userid,startTime,endTime,page);
	}
	
	 /**
	 * 人员最近位置
	 */
	@PostMapping("/recent")
	public Object getRecentPosition() {
		return new BaseResponse<Object>(200, "success", userPositionService.getRecentPosition());
	}
}
 