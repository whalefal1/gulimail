package com.atguigu.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})

public class GulimailCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailCommonApplication.class, args);
    }

}
