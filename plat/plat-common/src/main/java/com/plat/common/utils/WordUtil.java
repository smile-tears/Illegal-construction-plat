package com.plat.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
}
