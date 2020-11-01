package com.plat.common.web;
import com.plat.common.entity.Page;
import com.plat.common.entity.JobMask;
import com.plat.common.service.JobMaskService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobMask")
public class JobMaskController {

    @Autowired
    private JobMaskService jobMaskService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody JobMask jobMask){
        return jobMaskService.save(jobMask);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return jobMaskService.deleteByIds(ids);
    }

	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody JobMask jobMask) {
		return jobMaskService.Update(jobMask);
	}

    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(JobMask jobMask, Page page) {
		return jobMaskService.find(jobMask,page);
	}
}
