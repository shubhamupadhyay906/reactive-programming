package com.reactive.reactiveproject.utils;

import com.reactive.reactiveproject.entity.Book;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

public class ProductUtils {

    private ProductUtils() {
    }

    public static boolean isBookEmpty(Book book) {
        return Stream.of(book.getName(), book.getPublisher(), book.getAuthor(), book.getDescription())
                .allMatch(StringUtils::isNotEmpty);
    }
}
