package com.hjj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@MapperScan(basePackages = {"com.hjj.good.mapper"})
@EnableCaching
public class LcloudGoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(LcloudGoodApplication.class, args);
    }


}
