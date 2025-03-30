package com.mall;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author 李茂鑫
 * @Date 2025/1/17 17:10
 */
@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        String port = environment.getProperty("server.port");
        log.info("""
                
                ----------------------------------------------
                Application is running Access Url:
                Base:      http://127.0.0.1:{}
                Doc:       http://127.0.0.1:{}/doc.html
                ----------------------------------------------
                """,port,port);
    }
}
