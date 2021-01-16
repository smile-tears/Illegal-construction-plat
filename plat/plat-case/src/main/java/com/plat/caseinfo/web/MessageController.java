package com.plat.caseinfo.web;
import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.Message;
import com.plat.caseinfo.service.MessageService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody Message message){
        return messageService.save(message);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return messageService.deleteByIds(ids);
    }
	
 
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(Message message, Page page,String startDate,String endDate, HttpServletRequest request) {
		return messageService.find(message,page,startDate,endDate,request);
	}
 
}
