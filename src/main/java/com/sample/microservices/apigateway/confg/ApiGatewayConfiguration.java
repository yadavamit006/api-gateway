package com.sample.microservices.apigateway.confg;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatwayRouter(RouteLocatorBuilder locatorBuilder) {
        return locatorBuilder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f.addRequestHeader("MyHeader", "MyURI")
                                .addRequestHeader("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))

                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))

                .route(p -> p.path("/currency-conversion/**", "/currency-conversion-feign/**")
                        .uri("lb://currency-conversion-service")).build();
    }
}
