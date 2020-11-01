package com.plat.common.service;


import java.util.List;

import com.plat.common.entity.Page;
import com.plat.common.entity.Menu;

public interface MenuService {
	public Object save(Menu menu);

	public Object deleteByIds(String[] ids);

	public Object Update(Menu menu);

	public Object find(Menu menu, Page page);
	
	public Object tree(String id);
	
	public Object userTree(String userId,String supMenuid);
}