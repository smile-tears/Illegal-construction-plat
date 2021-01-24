package com.plat.caseinfo.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface PoiService {

	public Object uploadCompanyList(MultipartFile file) throws IOException;
	
	public Object uploadUserList(MultipartFile file) throws IOException;
}
