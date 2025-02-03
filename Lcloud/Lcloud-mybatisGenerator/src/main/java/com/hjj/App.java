package com.hjj;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/cloud_god", "root", "abc123")
                .globalConfig(builder -> builder
                        .author("hjj")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                        .commentDate("yyyy-MM-dd")
                )
                .packageConfig(builder -> builder
                        .parent("com.hjj.mybatisplus")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                )
                .strategyConfig(builder -> builder
                        .entityBuilder()
                        .enableLombok()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
