package com.atguigu.gulimail.seckill.scheduled;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling

//@EnableScheduling开启定时任务
//@Scheduled开启一个定时任务
public class HelloSchedule {

    @Scheduled(cron = " * * * * ?")
        public void hello() {
            log.info("hello...");
        }

}
