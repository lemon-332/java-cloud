package com.hjj.cart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = {"com.hjj.good.feign"})
@MapperScan("com.hjj.cart.mapper")
public class LcloudCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(LcloudCartApplication.class, args);
    }
}
