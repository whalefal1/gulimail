package com.atguigu.gulimail.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Jerry
 */


@EnableRedisHttpSession
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.atguigu.gulimail.search.feign")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

public class GulimailSearchApplication {

  public static void main(String[] args) {
    SpringApplication.run(GulimailSearchApplication.class, args);
  }
}
