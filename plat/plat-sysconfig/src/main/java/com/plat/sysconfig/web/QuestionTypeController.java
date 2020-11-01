package com.plat.sysconfig.web;
import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.QuestionType;
import com.plat.sysconfig.service.QuestionTypeService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	/**
	 * 获取问题类型树
	 */
	@PostMapping("/tree")
	public Object getTree(@RequestParam(value = "id", defaultValue = "0") String id) {
		return questionTypeService.tree(id);
	}
}
