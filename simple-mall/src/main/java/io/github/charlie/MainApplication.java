package io.github.charlie;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 启动类
 */
@SpringBootApplication
@MapperScan("io.github.charlie.mapper")
@Slf4j
public class MainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        log.info("""
                                                \s
                         -------------------------------------------------
                             Application is running! Access URLs:
                             Local:    http://localhost:{}
                             Doc:      http://localhost:{}/doc.html
                         -------------------------------------------------
                        \s""",
                environment.getProperty("server.port"),
                environment.getProperty("server.port")
        );
    }
}
