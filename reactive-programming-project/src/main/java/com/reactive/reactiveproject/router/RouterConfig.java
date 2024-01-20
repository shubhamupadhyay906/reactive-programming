package com.reactive.reactiveproject.router;

import com.reactive.reactiveproject.handler.BookHandler;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Bean
    public WebProperties.Resources webProperties() {
        return new WebProperties.Resources();
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(BookHandler bookHandler) {
        return RouterFunctions.route()
                .POST("/router/add", bookHandler::createRouterBook)
                .GET("/router/book", bookHandler::getAllBooks)
                .GET("/router/book/{bookId}", bookHandler::getBookById)
                .DELETE("/router/book/{bookId}", bookHandler::deleteBookById)
                .build();
    }
}
