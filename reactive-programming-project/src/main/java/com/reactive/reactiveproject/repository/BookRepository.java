package com.reactive.reactiveproject.repository;

import com.reactive.reactiveproject.entity.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {

    Flux<Book> findByName(String name);

    //get data using query
    @Query("select * from book_details where name  LIKE :title")
    Flux<Book> getAllBooksByTitle(String title);
}
