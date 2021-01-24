package com.plat.main;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.plat.caseinfo.web.WebSocketServerConfig;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { MultipartAutoConfiguration.class }) // 附件
//不加扫描注解默认扫描当前启动类所在的包及其子包，因涉及到不同的module，所以使用注解指定包位置
@ComponentScan(basePackages = { "com.plat.main", "com.plat.common",
		"com.plat.sysconfig", "com.plat.caseinfo" })
//扫描jpa Repository注解，
@EntityScan(basePackages = { "com.plat.common.entity", "com.plat.sysconfig.entity", "com.plat.caseinfo.entity" })
@EnableJpaRepositories(basePackages = { "com.plat.common.dao", "com.plat.sysconfig.dao", "com.plat.caseinfo.dao" })
@EnableTransactionManagement
public class Application {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		WebSocketServerConfig.setApplicationContext(context);
	}
	
	@Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }
 
    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(8088);
        return connector;
    }
}
