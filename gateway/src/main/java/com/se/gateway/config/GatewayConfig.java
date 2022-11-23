package com.se.gateway.config;

import com.se.gateway.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service",r -> r.path("/api/auth/**","/api/users/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://auth-service"))
                .route("image-service",r -> r.path("/api/images/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://image-service"))
                .route("address-service",r -> r.path("/api/addresses/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://address-service"))
                .route("product-service",r -> r.path("/api/products/**","/api/categories/**","/api/brands/**","/api/cart-items/**", "/api/carts/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://product-service"))
                .route("order-service",r -> r.path("/api/orders/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://order-service"))
                .build();
    }

}
