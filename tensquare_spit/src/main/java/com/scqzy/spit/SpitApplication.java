package com.scqzy.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import com.scqzy.util.IdWorker;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/14 19:21
 */
@SpringBootApplication
@EnableEurekaClient
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class, args);
    }

    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }
}
