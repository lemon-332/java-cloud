package com.hjj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = {"com.hjj.good.mapper"})
public class LcloudGoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(LcloudGoodApplication.class, args);
    }


}
