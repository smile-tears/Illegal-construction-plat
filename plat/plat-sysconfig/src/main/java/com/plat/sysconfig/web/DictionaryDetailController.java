package com.plat.sysconfig.web;
import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.DictionaryDetail;
import com.plat.sysconfig.service.DictionaryDetailService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典
 */
@RestController
@RequestMapping("/dictionaryDetail")
public class DictionaryDetailController {

    @Autowired
    private DictionaryDetailService dictionaryDetailService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody DictionaryDetail dictionaryDetail){
        return dictionaryDetailService.save(dictionaryDetail);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return dictionaryDetailService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody DictionaryDetail dictionaryDetail) {
		return dictionaryDetailService.Update(dictionaryDetail);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(DictionaryDetail dictionaryDetail,Page page) {
		return dictionaryDetailService.find(dictionaryDetail,page);
	}

}
