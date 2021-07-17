package com.scqzy.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/15 21:41
 */
@SpringBootApplication
@EnableEurekaClient
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class);
    }
}
