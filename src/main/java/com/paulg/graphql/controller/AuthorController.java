package com.paulg.graphql.controller;

import com.paulg.graphql.entity.Author;
import com.paulg.graphql.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @QueryMapping
    public Author author(@Argument Long id) {
        return authorService.findAuthorById(id);
    }

    @QueryMapping
    public List<Author> authors() {
        return authorService.findAll();
    }
}
