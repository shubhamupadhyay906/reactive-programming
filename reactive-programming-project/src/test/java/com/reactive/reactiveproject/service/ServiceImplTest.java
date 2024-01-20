package com.reactive.reactiveproject.service;

import com.reactive.reactiveproject.entity.Book;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class ServiceImplTest {

    private final ServiceImpl service = Mockito.mock(ServiceImpl.class);

    @Test
    void success_testCreateBook() {
        Mockito.when(service.createBook(any())).thenReturn(Mono.just(buildBook()));
        Mono<Book> flux = service.createBook(buildBook());
        StepVerifier.create(flux)
                .consumeNextWith(book -> {
                    assertEquals(1, book.getBookId());
                    assertEquals("Ravi", book.getAuthor());
                    assertEquals("Developer", book.getName());
                    assertEquals("SIA", book.getPublisher());
                })
                .verifyComplete();
    }

    @Test
    void success_testBookSingleList() {
        Mockito.when(service.getAllBook()).thenReturn(Flux.just(buildBook()));
        Flux<Book> flux = service.getAllBook();
        StepVerifier.create(flux)
                .expectNextCount(1)
                .verifyComplete();
    }

    private Book buildBook() {
        Book book = new Book();
        book.setBookId(1);
        book.setName("Developer");
        book.setAuthor("Ravi");
        book.setDescription("Java Developer Book");
        book.setPublisher("SIA");
        return book;
    }
}