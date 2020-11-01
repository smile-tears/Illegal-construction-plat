package com.plat.sysconfig.web;
import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.Sample;
import com.plat.sysconfig.service.SampleService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody Sample sample){
        return sampleService.save(sample);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return sampleService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody Sample sample) {
		return sampleService.Update(sample);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(Sample sample, Page page) {
		return sampleService.find(sample,page);
	}
	
	/**
	 * 获取样本树
	 */
	@PostMapping("/tree")
	public Object getTree(@RequestParam(value = "id", defaultValue = "0") String id) {
		return sampleService.tree(id);
	}
}
