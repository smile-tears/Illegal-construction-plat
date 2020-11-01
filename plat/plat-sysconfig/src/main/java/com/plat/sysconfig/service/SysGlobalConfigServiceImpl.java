package com.plat.sysconfig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.plat.common.entity.BaseResponse;
import com.plat.sysconfig.dao.SysGlobalConfigRepository;
import com.plat.sysconfig.entity.SysGlobalConfig;

@Service
public class SysGlobalConfigServiceImpl implements SysGlobalConfigService {

	@Autowired
	SysGlobalConfigRepository sysGlobalConfigRepository;

	@Override
	public Object save(SysGlobalConfig sysGlobalConfig) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", sysGlobalConfigRepository.save(sysGlobalConfig));
	}

	@Override
	public Object Update(SysGlobalConfig sysGlobalConfig) {
		// TODO Auto-generated method stub
		return new BaseResponse<>(200, "success", sysGlobalConfigRepository.save(sysGlobalConfig));
	}

	@Override
	public Object find(SysGlobalConfig sysGlobalConfig) {
		// TODO Auto-generated method stub
		Example<SysGlobalConfig> example = Example.of(sysGlobalConfig);
		return new BaseResponse<>(200, "success", sysGlobalConfigRepository.findAll(example));
	}

}
