package com.atguigu.gulimail.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan("com.atguigu.gulimail.member.dao")
//开启服务注册发现功能
@EnableDiscoveryClient
//开启服务远程调用功能
@EnableFeignClients(basePackages = "com.atguigu.gulimail.member.feign")
@EnableRedisHttpSession

public class GulimailMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailMemberApplication.class, args);
    }

}
