package com.mall;

/**
 * @author 李茂鑫
 * @Date 2025/1/18 10:27
 * @Descripition   //数据库文档生成器
 */
import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ScrewTest {

    @Test
    void generate() {
        // 生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径，自己mac本地的地址，这里需要自己更换下路径
                .fileOutputDir("E:\\桌面\\sql\\")
                // 打开目录
                .openOutputDir(false)
                // 文件类型 HTML/WORD/MD 三种格式
                .fileType(EngineFileType.WORD)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker).build();

            HikariDataSource hikariDataSource = new HikariDataSource();
            //设置数据库连接
            hikariDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/zygcs_mall?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true");
            hikariDataSource.setUsername("root");
            hikariDataSource.setPassword("123456");
            // 生成文档配置（包含以下自定义版本号、描述等配置连接）
            Configuration config = Configuration.builder()
                    .version("1.0.1")
                    .description("数据库设计文档")
                    .dataSource(hikariDataSource)
                    .engineConfig(engineConfig)
                    .produceConfig(getProcessConfig())
                    .build();

            // 执行生成
            new DocumentationExecute(config).execute();
        }


    /**
     * 配置想要生成的表+ 配置想要忽略的表
     * @return 生成表配置
     */
    public static ProcessConfig getProcessConfig(){
        // 忽略表名
        List<String> ignoreTableName = null;
        // 忽略表前缀
        List<String> ignorePrefix = null;
        // 忽略表后缀
        List<String> ignoreSuffix = null;
        return ProcessConfig.builder()
                //根据名称指定表生成 我需要生成所有表 这里暂时不设置
                .designatedTableName(new ArrayList<>())
                //根据表前缀生成 我需要生成所有表 这里暂时不设置
                .designatedTablePrefix(new ArrayList<>())
                //根据表后缀生成 我需要生成所有表 这里暂时不设置
                .designatedTableSuffix(new ArrayList<>())
                //忽略表名
                .ignoreTableName(null)
                //忽略表前缀
                .ignoreTablePrefix(null)
                //忽略表后缀
                .ignoreTableSuffix(null).build();
    }
}

