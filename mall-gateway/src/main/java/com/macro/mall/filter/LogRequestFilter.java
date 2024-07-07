package com.macro.mall.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 请求日志过滤器
 */
@Component
public class LogRequestFilter implements WebFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogRequestFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        //详细打印网关请求日志
        LOGGER.info("Request: {}", exchange.getRequest().getURI());
        return chain.filter(exchange).doOnSuccess(aVoid -> {
            LOGGER.info("Response detail: {}", exchange.getResponse());
        });
    }
}
