package com.plat.main;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.MimeType;

import java.nio.charset.Charset;

/**
 * Created by stone on 2020/6/13.
 */
@ConfigurationProperties(prefix = "spring.thymeleaf")
public class ThymeleafProperties {

    private static final Charset DEFAULT_ENCODING = Charset.forName("UTF-8");

    private static final MimeType DEFAULT_CONTENT_TYPE = MimeType.valueOf("text/html");

    public static final String DEFAULT_PREFIX = "classpath:/templates/";

    public static final String DEFAULT_SUFFIX = ".html";
    //上面是springmvc中的前缀和后缀
}
