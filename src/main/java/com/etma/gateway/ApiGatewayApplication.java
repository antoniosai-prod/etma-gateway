package com.etma.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.etma.gateway")
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	public String helloWorld() {
		String name = "Yoga";

		return name;
	}

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//
//		RouteLocatorBuilder.Builder routeBuilder = builder.routes();
//
//		routeBuilder.route("user-management",
//				r -> r.path("/user-management/**")
//						.uri("http://localhost:6001"));
//		routeBuilder.route("master-data",
//				r -> r.path("/master-data/**")
//						.uri("http://localhost:8000"));
//		return routeBuilder.build();
//	}

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route("master_data_route", r -> r
//						.path("/master-data/**")
//						.uri("http://localhost:8000"))
//				.route("user_management_route", r -> r
//						.path("/user-management/**")
//						.uri("http://localhost:6001"))
//				.build();
//	}
}
