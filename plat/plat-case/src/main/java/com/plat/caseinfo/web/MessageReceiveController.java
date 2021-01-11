package com.plat.caseinfo.web;
import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.MessageReceive;
import com.plat.caseinfo.service.MessageReceiveService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/messageReceive")
public class MessageReceiveController {

    @Autowired
    private MessageReceiveService messageReceiveService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody MessageReceive messageReceive){
        return messageReceiveService.save(messageReceive);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return messageReceiveService.deleteByIds(ids);
    }
 
    @PostMapping("/read")
    public Object read(String id) {
    	return messageReceiveService.read(id);
    }
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(MessageReceive messageReceive, Page page) {
		return messageReceiveService.find(messageReceive,page);
	}
 
}
