package com.scqzy.tensquare_gateway;

import com.scqzy.tensquare_gateway.config.MyGatewayGlobalFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public MyGatewayGlobalFilter myGatewayGlobalFilter() {
        return new MyGatewayGlobalFilter();
    }
}
