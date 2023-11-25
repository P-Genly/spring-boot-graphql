package com.paulg.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Author {

    private int id;
    private String lastName;
    private String firstName;

    public static List<Author> authors() {
        return List.of(
                new Author(1, "Rowling", "JK"),
                new Author(2, "Hawking", "Stephen"),
                new Author(3, "Delaney", "Joseph")
        );
    }

    public static Author getAuthorById(int id) {
        return authors().stream()
                .filter(author -> author.getId() == id)
                .findFirst().orElse(null);
    }
}
