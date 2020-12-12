package com.plat.common.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.plat.common.entity.BaseResponse;

import net.coobird.thumbnailator.Thumbnails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 附件上传
 */
@RestController
public class FileController {

	public static String fileAbsolute = "C:\\Users\\Administrator\\Desktop\\java\\file\\";
	public static String imageAbsolute = "C:\\Users\\Administrator\\Desktop\\java\\avatar\\";
	
	public static String getUUID() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 上传文件方法
	 */
	public static final String filePath = "file/";
	@PostMapping(value = "/upload")
	public Object upload(HttpServletRequest request, MultipartFile file) {
		JSONObject result = new JSONObject();
		try {
			String url = "";
			String uuid = getUUID();
			String filename = file.getOriginalFilename();
			String suffix = filename.substring(filename.lastIndexOf(".") + 1);
			File dir = new File(filePath);
			if (!dir.exists())
				dir.mkdir();
			// 保存文件对象 加上uuid是为了防止文件重名
			url = filePath + uuid + "." + suffix;
			File serverFile = new File(url);
			file.transferTo(serverFile);
			result.put("url", "/" + url);
			result.put("fileName", filename);
			result.put("fileType", 0);
		} catch (Exception e) {
			// 打印错误堆栈信息
			e.printStackTrace();
			return new BaseResponse<>(200, "上传失败！");
		}

		return new BaseResponse<>(200, "上传成功！", result);
	}

	public static final String avatarPath = "avatar/";
	
	@PostMapping(value = "/upload-avatar")
	public Object uploadThumbnail(@RequestParam("avatar") MultipartFile avatar) {
		String originalFilename = avatar.getOriginalFilename();
		String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		String originFilePath = "", thumbFilePath = "";
		String uuid = getUUID();
		JSONObject result = new JSONObject();
		try {
			// 创建文件目录
			File directory = new File(avatarPath);
			if (!directory.exists())
				directory.mkdirs();
			// 上传原图
			originFilePath = avatarPath + uuid + "_origin."+suffix;
			avatar.transferTo(new File(originFilePath));
			// 上传缩略图
			thumbFilePath = avatarPath + uuid + "_thumb";
			Thumbnails.of(avatar.getInputStream()).scale(0.5f).outputQuality(0.15f).outputFormat(suffix)
					.toFile(thumbFilePath);
			result.put("url", "/" + originFilePath);
			result.put("thumbUrl", "/" + thumbFilePath + "." + suffix);
			result.put("fileName", originalFilename);
			result.put("fileType", 1);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return new BaseResponse<>(200, "上传失败！");
		}
		
		return new BaseResponse<>(200, "上传成功！", result);

	}
	
	@GetMapping("/avatar/{url}")
	public void readImage(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable(value = "url") String url) throws IOException {
		FileInputStream is = new FileInputStream(avatarPath + url);
		int i = is.available(); // 得到文件大小
		byte data[] = new byte[i];
		is.read(data); // 读数据
		is.close();
		response.setContentType("image/*"); // 设置返回的文件类型
		OutputStream os = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
		os.write(data); // 输出数据
		os.close();
	}
	@GetMapping("/file/{url}")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable(value = "url") String url) throws IOException {
		FileInputStream is = new FileInputStream(filePath + url);
		int i = is.available(); // 得到文件大小
		byte data[] = new byte[i];
		is.read(data); // 读数据
		is.close();
		response.setContentType("image/*"); // 设置返回的文件类型
		OutputStream os = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
		os.write(data); // 输出数据
		os.close();
	}
	
	public static void main(String[] args) {
		String filename = "123.jpg"; 
		String prefix = filename.substring(0,filename.lastIndexOf(".") );
		String suffix = filename.substring(filename.lastIndexOf(".") + 1);
		System.out.println(prefix);
		System.out.println(suffix);
	}
}