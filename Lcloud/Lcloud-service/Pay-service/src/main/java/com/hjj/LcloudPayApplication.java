package com.hjj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
@MapperScan("com.hjj.pay.mapper")
public class LcloudPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LcloudPayApplication.class, args);
    }
}
