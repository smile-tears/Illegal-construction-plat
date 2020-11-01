package com.plat.sysconfig.web;
import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.GridCommunity;
import com.plat.sysconfig.service.GridCommunityService;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/gridCommunity")
public class GridCommunityController {

    @Autowired
    private GridCommunityService gridCommunityService;

    /**
    * 新增
    */
    @PostMapping("/post")
    public Object save(@RequestBody GridCommunity gridCommunity){
        return gridCommunityService.save(gridCommunity);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object deleteById(@RequestBody String[] ids){
        return gridCommunityService.deleteByIds(ids);
    }
	
	/**
	 * 更新
	 */
	@PostMapping(value = "/put")
	public Object Update (@RequestBody GridCommunity gridCommunity) {
		return gridCommunityService.Update(gridCommunity);
	}
	
    /**
	 * 查询
	 */
	@PostMapping("/find")
	public Object find(GridCommunity gridCommunity, Page page) {
		return gridCommunityService.find(gridCommunity,page);
	}
    /**
     * 获取菜单树
     */
    @PostMapping("/tree")
    public Object getTree(@RequestParam(value = "id", defaultValue = "0") String id) {
        return gridCommunityService.tree(id);
    }
    /**
     * 获取树id对应的名称
     */
    @PostMapping("/idAndName")
    public Object getIdAndName() {
        return gridCommunityService.getIdAndName();
    }
    /**
     * 获取人员id对应的名称
     */
    @PostMapping("/userIdAndName")
    public Object getUserIdAndName() {
        return gridCommunityService.getUserIdAndName();
    }

    /**
     * 检查名称是否重复
     */
    @PostMapping("/checkGridName")
    public Object checkGridName(@RequestParam(value = "name") String name,@RequestParam(value = "id") String id) {
        return gridCommunityService.checkGridName(name,id);
    }

    /**
     * 获取电话号码
     */
    @PostMapping("/getTelephone")
    public Object getTelephone(@RequestParam(value = "id") String id) {
        return gridCommunityService.getTelephone(id);
    }


}
