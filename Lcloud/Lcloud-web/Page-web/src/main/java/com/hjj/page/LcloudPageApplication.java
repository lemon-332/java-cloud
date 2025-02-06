package com.hjj.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LcloudPageApplication {
    public static void main(String[] args) {
        SpringApplication.run(LcloudPageApplication.class, args);
    }
}
