package com.scqzy.tensquare_gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/18 10:00
 */
@Slf4j
public class MyGatewayGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        // 指定此过滤器位于NettyWriteResponseFilter之后
        // 即待处理完响应体后接着处理响应头
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER + 1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.defer(() -> {
            /*
            跨域bug处理
             */
            exchange.getResponse().getHeaders().entrySet().stream()
                    .filter(kv -> kv.getValue() != null && kv.getValue().size() > 1)
                    .filter(kv -> kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
                            || kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS))
                    .forEach(kv -> kv.setValue(Collections.singletonList(kv.getValue().get(0))));
            /*
            过滤token
             */
            String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
            log.info(authorization);
            if (StringUtils.isBlank(authorization) || !authorization.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange);
        }));
    }

}