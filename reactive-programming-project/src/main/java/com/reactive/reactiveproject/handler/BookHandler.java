package com.reactive.reactiveproject.handler;

import com.reactive.reactiveproject.entity.Book;
import com.reactive.reactiveproject.exception.BookException;
import com.reactive.reactiveproject.service.BookService;
import com.reactive.reactiveproject.utils.ProductUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.reactive.reactiveproject.utils.ProductConstants.MISSING_FIELDS;
import static com.reactive.reactiveproject.utils.ProductConstants.NOT_FOUND;
import static java.lang.Integer.parseInt;

@Service
public class BookHandler {
    private static final Logger logger = LoggerFactory.getLogger(BookHandler.class);
    @Autowired
    private final BookService bookService;

    public BookHandler(BookService bookService) {
        this.bookService = bookService;
    }

    public Mono<ServerResponse> createRouterBook(ServerRequest serverRequest) {
        final Mono<Book> addedBook = serverRequest.bodyToMono(Book.class)
                .filter(ProductUtils::isBookEmpty)
                .flatMap(bookService::createBook)
                .switchIfEmpty(Mono.error(new BookException(MISSING_FIELDS)));
        return ServerResponse.ok().body(addedBook, Book.class);
    }

    public Mono<ServerResponse> getAllBooks(ServerRequest serverRequest) {
        final Flux<Book> allBooks = bookService.getAllBook();
        return ServerResponse.ok().body(allBooks, Book.class);
    }

    public Mono<ServerResponse> getBookById(ServerRequest serverRequest) {
        int bookId = parseInt(serverRequest.pathVariable("bookId"));
        final Mono<Book> book = bookService.getBookById(bookId)
                .switchIfEmpty(Mono.error(new BookException(NOT_FOUND)));
        return ServerResponse.ok().body(book, Book.class);
    }

    public Mono<ServerResponse> deleteBookById(ServerRequest serverRequest) {
        int bookId = parseInt(serverRequest.pathVariable("bookId"));
        Mono<Void> book = bookService.delete(bookId)
                .switchIfEmpty(Mono.error(new BookException("Deleted id not found")));
        logger.info("delete of book by : {}", book);
        return ServerResponse.ok().body(book, Book.class);
    }
}
