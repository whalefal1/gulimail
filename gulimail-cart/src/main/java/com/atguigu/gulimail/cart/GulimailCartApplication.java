package com.atguigu.gulimail.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
@EnableDiscoveryClient
@EnableFeignClients
@EnableRedisHttpSession
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GulimailCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailCartApplication.class, args);
    }

}
