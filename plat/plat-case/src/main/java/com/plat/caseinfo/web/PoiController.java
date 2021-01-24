package com.plat.caseinfo.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.plat.caseinfo.service.PoiService;

@RestController
public class PoiController {

	@Autowired
	private PoiService poiService;
	
	@PostMapping("/upload/company")
	public Object uploadCompanyList(@RequestParam("file") MultipartFile file) throws IOException {
		return poiService.uploadCompanyList(file);
	}
	
	@PostMapping("/upload/user")
	public Object uploadUserList(@RequestParam("file") MultipartFile file) throws IOException {
		return poiService.uploadUserList(file);
	}
}
