package com.paulg.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class Book {

    private int id;
    private String title;
    private int pageCount;
    private Author author;

    public static List<Book> books() {
        return Arrays.asList(
                new Book(1, "Harry Potter and philosophical stone", 223, Author.getAuthorById(1)),
                new Book(2, "A short story of time", 256, Author.getAuthorById(2)),
                new Book(3, "The spook's apprentice ", 432, Author.getAuthorById(3))
        );
    }

    public static Book getById(int id) {
        return books().stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }
}
