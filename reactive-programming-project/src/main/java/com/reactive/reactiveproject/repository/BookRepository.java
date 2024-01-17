package com.reactive.reactiveproject.repository;

import com.reactive.reactiveproject.entity.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {
}
