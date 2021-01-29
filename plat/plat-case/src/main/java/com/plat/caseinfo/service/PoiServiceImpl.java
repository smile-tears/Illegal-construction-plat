package com.plat.caseinfo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.plat.common.dao.DepartmentRepository;
import com.plat.common.dao.UserRepository;
import com.plat.common.entity.BaseResponse;
import com.plat.common.entity.Department;
import com.plat.common.entity.User;
import com.plat.common.utils.EncryptionUtils;
import com.plat.common.utils.StringUtil;
import com.plat.sysconfig.dao.CompanyManageRepository;
import com.plat.sysconfig.dao.GridCommunityRepository;
import com.plat.sysconfig.entity.CompanyManage;
import com.plat.sysconfig.entity.GridCommunity;
import com.plat.sysconfig.service.CompanyManageService;

@Service
@Transactional
public class PoiServiceImpl implements PoiService {

	@Autowired
	private CompanyManageRepository companyManageRepository;

	@Autowired
	private CompanyManageService companyManageService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GridCommunityRepository gridCommunityRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Object uploadCompanyList(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		POIFSFileSystem poifsFileSystem = new POIFSFileSystem(file.getInputStream());
		HSSFWorkbook workbook = HSSFWorkbookFactory.createWorkbook(poifsFileSystem);
		poifsFileSystem.close();
		HSSFSheet sheet = workbook.getSheetAt(0);// 第一个sheet
		int rowLength = sheet.getPhysicalNumberOfRows(); // 总行数
		int colLength = sheet.getRow(0).getPhysicalNumberOfCells(); // 总列数
		for (int i = 1; i < rowLength; i++) {
			HSSFRow row = sheet.getRow(i);
			for (int j = 0; j < colLength; j++) {
				HSSFCell cell = row.getCell(j);
				// Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串时就有可能报异常：
				// Cannot get a STRING value from a NUMERIC cell
				// 将所有的需要读的Cell表格设置为String格式
				if (cell != null) {
					cell.setCellType(CellType.STRING);
					// System.out.print(cell.getStringCellValue() + "\t");
				}
			}
			String id = companyManageRepository.getCompanyIdByName(StringUtil.null2String(row.getCell(0)));
			CompanyManage companyManage = new CompanyManage();
			companyManage.setId(id);
			companyManage.setCompanyName(StringUtil.null2String(row.getCell(0)));
			companyManage.setAddress(StringUtil.null2String(row.getCell(1)));
			companyManage.setLegalPerson(StringUtil.null2String(row.getCell(2)));

			String safetyOffice = StringUtil.null2String(row.getCell(3));
			User user = userRepository.getUserByName(safetyOffice);
			if (user != null)
				companyManage.setSafetyOffice(user.getId());// 人员姓名转id

			companyManage.setMobile(StringUtil.null2String(row.getCell(4)));
			companyManage.setQygm(StringUtil.null2String(row.getCell(5)));
			companyManage.setLevel(StringUtil.null2String(row.getCell(6)));
			companyManage.setRemark(StringUtil.null2String(row.getCell(7)));
			companyManage.setLng(StringUtil.null2String(row.getCell(8)));
			companyManage.setLat(StringUtil.null2String(row.getCell(9)));

			String gridName = StringUtil.null2String(row.getCell(10));
			GridCommunity gridCommunity = gridCommunityRepository.getGridByGridName(gridName);
			if (gridCommunity != null)
				companyManage.setGrid(gridCommunity.getId());// 网格名称转网格id

//        	String showOrder = row.getCell(11).getStringCellValue();
//        	if (!StringUtils.isEmpty(showOrder)) companyManage.setShowOrder(Integer.parseInt(showOrder));
			companyManage.setDelTag(1);

			System.out.println("=============" + JSON.toJSONString(companyManage));
			companyManageRepository.save(companyManage);

		}
		return new BaseResponse<>(200, "success");
	}

	@Override
	public Object uploadUserList(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		POIFSFileSystem poifsFileSystem = new POIFSFileSystem(file.getInputStream());
		HSSFWorkbook workbook = HSSFWorkbookFactory.createWorkbook(poifsFileSystem);
		poifsFileSystem.close();
		HSSFSheet sheet = workbook.getSheetAt(0);// 第一个sheet
		int rowLength = sheet.getPhysicalNumberOfRows(); // 总行数
		int colLength = sheet.getRow(0).getPhysicalNumberOfCells(); // 总列数
		for (int i = 1; i < rowLength; i++) {
			HSSFRow row = sheet.getRow(i);
			for (int j = 0; j < colLength; j++) {
				HSSFCell cell = row.getCell(j);
				// Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串时就有可能报异常：
				// Cannot get a STRING value from a NUMERIC cell
				// 将所有的需要读的Cell表格设置为String格式
				if (cell != null) {
					cell.setCellType(CellType.STRING);
					// System.out.print(cell.getStringCellValue() + "\t");
				}
			}
			User user = new User();
			User user2 = userRepository.getUserByName(StringUtil.null2String(row.getCell(0)));
			if (user2 != null) user.setId(user2.getId());
			user.setName(StringUtil.null2String(row.getCell(0)));
			
			Department department = departmentRepository.getDepartmentByName(StringUtil.null2String(row.getCell(1)));
			user.setDepartmentId(department.getId());
			
			user.setUsername(StringUtil.null2String(row.getCell(2)));
			
			SimpleHash sh = new SimpleHash(EncryptionUtils.algorithmName, StringUtil.null2String(row.getCell(3)), 
					EncryptionUtils.salt, EncryptionUtils.hashIterations);
			user.setPassword(sh.toHex());
			
			user.setIdNumber(StringUtil.null2String(row.getCell(4)));
			
			String sex = StringUtil.null2String(row.getCell(5));
			if (sex.equals("男")) user.setSex(0);
			else if (sex.equals("女")) user.setSex(1);
			
			user.setTelephone(StringUtil.null2String(row.getCell(6)));
			
			String status = StringUtil.null2String(row.getCell(7)) ;
			user.setStatus(status.equals("正式") ? 1 : 0);
			
			user.setWorkcode(StringUtil.null2String(row.getCell(8)));
			
			String showOrder = StringUtil.null2String(row.getCell(9));
			if(!showOrder.equals("")) user.setShowOrder(Integer.parseInt(showOrder));
			
			user.setDelTag(1);
			userRepository.save(user);
			
			
		}
		return new BaseResponse<>(200, "success");
	}

}
