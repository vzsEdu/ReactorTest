package com.vzs.reactive.reactive;

import com.vzs.reactive.reactive.flux.ProjectHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@SpringBootApplication
public class ReactiveApplication {

	@Bean
	RouterFunction<?> routes111(ProjectHandler projectHandler) {
		return RouterFunctions.route(GET("/hello"), serverRequest -> ServerResponse.ok().body(fromObject("hello world"))).
				andRoute(GET("/send-times"), projectHandler::sendTimePerSec);
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

}

