package com.plat.sysconfig.web;
import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.QuestionType;
import com.plat.sysconfig.service.QuestionTypeService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/questionType")
public class QuestionTypeController {

    @Autowired
    private QuestionTypeService questionTypeService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody QuestionType questionType){
        return questionTypeService.save(questionType);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return questionTypeService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody QuestionType questionType) {
		return questionTypeService.Update(questionType);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(QuestionType questionType, Page page) {
		return questionTypeService.find(questionType,page);
	}
}
