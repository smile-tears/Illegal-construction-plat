package com.plat.sysconfig.service;

import com.plat.common.entity.Page;
import com.plat.sysconfig.entity.GridCommunity;

public interface GridCommunityService {
	public Object save(GridCommunity gridCommunity);

	public Object deleteByIds(String[] ids);

	public Object Update(GridCommunity gridCommunity);

	public Object find(GridCommunity gridCommunity, Page page);

	public Object tree(String id);

	public Object getIdAndName();

	public Object getUserIdAndName();

	public Object checkGridName(String name,String id);

	public Object getTelephone(String id);
}