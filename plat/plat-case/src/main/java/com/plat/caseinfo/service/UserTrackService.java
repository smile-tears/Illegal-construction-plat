package com.plat.caseinfo.service;

import com.plat.common.entity.Page;
import com.plat.caseinfo.entity.UserTrack;

public interface UserTrackService {
	public Object save(UserTrack userTrack);

	public Object find(String userid,String starttime,String endtime, Page page);

}