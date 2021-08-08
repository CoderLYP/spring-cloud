package com.louis.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_louis_1", r->r.path("/guonei").uri("http://news.baidu.com/guonei"));
        routes.route("path_route_louis_2", r->r.path("/guoji").uri("http://news.baidu.com/guoji"));
        return routes.build();
    }
}
