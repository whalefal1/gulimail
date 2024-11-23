package com.atguigu.gulimail.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/*
* 1.整合MyBatis-Plus
*  1.1 导入依赖
* 2.配置
*  2.1 配置数据源
*      1.导入数据库驱动
*     2.在application.yml配置数据源相关信息
* 2.2 配置MyBatis-Plus
* 1.使用@MapperScan
* 2.告诉MyBatis-Plus，sql映射文件位置
 */
@EnableRedisHttpSession     //开启springsession
@EnableCaching
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.atguigu.gulimail.product.feign")
@MapperScan("com.atguigu.gulimail.product.dao")
@SpringBootApplication
public class GulimailProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailProductApplication.class, args);
    }

}
