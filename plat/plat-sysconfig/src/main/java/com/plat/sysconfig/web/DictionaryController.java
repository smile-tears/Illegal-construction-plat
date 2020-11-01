package com.plat.sysconfig.web;
import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.Dictionary;
import com.plat.sysconfig.service.DictionaryService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody Dictionary dictionary){
        return dictionaryService.save(dictionary);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return dictionaryService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody Dictionary dictionary) {
		return dictionaryService.Update(dictionary);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(Dictionary dictionary, Page page) {
		return dictionaryService.find(dictionary,page);
	}
}
