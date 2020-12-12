package com.plat.common.utils;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class WordUtil {

	public static String createWord(String filepath, Map<String, Object> data, String directory, String filename) {
		String msg = "";
		try {
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");

			configuration.setDirectoryForTemplateLoading(new File(directory));

			File outFile = new File(filepath);

			Template t = configuration.getTemplate(filename, "utf-8");

			Writer out = new BufferedWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8")));
			t.process(data, out);

			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			msg = "IOException:" + e.getMessage();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = "TemplateException:" + e.getMessage();
		}
		return msg;
	}
	
	/**
     * 获取图片对应的base64码
     * <p>
     * 图片
     *
     * @return 图片对应的base64码
     * @throws IOException
     * @date 2018/11/16 17:05
     */
    //获得图片的base64码
    public static String getImageBase(String src)  {
        if (src == null || src == "") {
            return "";
        }
        File file = new File(src);
        if (!file.exists()) {
            return "";
        }
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    
        return new String(Base64.getEncoder().encode(data));
    }
 
 
    /**
     * 远程读取image转换为Base64字符串
     *
     * @param imgUrl
     * @return
     */
    public static String Image2Base64(String imgUrl) {
		URL url = null;
        InputStream is = null;
        ByteArrayOutputStream outStream = null;
        HttpURLConnection httpUrl = null;
        try {
            url = new URL(imgUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            is = httpUrl.getInputStream();
 
            outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = is.read(buffer)) != -1) {
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            // 对字节数组Base64编码
            return new String(Base64.getEncoder().encode(outStream.toByteArray()));
            //return new BASE64Encoder().encode(outStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }  //下载
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }
        return imgUrl;
    }
}
