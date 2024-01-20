package com.reactive.reactiveproject;

import com.reactive.reactiveproject.controller.BookController;
import com.reactive.reactiveproject.entity.Book;
import com.reactive.reactiveproject.service.ServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebFluxTest(BookController.class)
class ReactiveProjectApplicationTests {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private ServiceImpl service;

    @Test
    void testAddBook() {
        Mono<Book> book = Mono.just(new Book(1, "Developer", "Developer Book", "SIA", "Ravi"));
        Mockito.when(service.createBook(any())).thenReturn(book);
        Flux<Book> responseBody = webTestClient.post().uri("/books")
                .body(Mono.just(book), Book.class)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Book.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .consumeNextWith(books -> {
                    assertEquals(1, books.getBookId());
                    assertEquals("Ravi", books.getAuthor());
                    assertEquals("Developer", books.getName());
                    assertEquals("SIA", books.getPublisher());
                })
                .verifyComplete();
    }

}
