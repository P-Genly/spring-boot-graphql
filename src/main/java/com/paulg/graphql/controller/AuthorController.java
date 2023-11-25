package com.paulg.graphql.controller;

import com.paulg.graphql.model.Author;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {

    @QueryMapping
    public Author author(@Argument int id) {
        return Author.getAuthorById(id);
    }

    @QueryMapping
    public List<Author> authors() {
        return Author.authors();
    }
}
