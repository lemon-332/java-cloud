package com.hjj.canal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = {"com.hjj.good.feign", "com.hjj.search.feign"})
public class LcloudCanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(LcloudCanalApplication.class, args);
    }
}
