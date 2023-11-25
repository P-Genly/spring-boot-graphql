package com.paulg.graphql.controller;

import com.paulg.graphql.model.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    @QueryMapping
    public Book book(@Argument int id) {
        return Book.getById(id);
    }

    @QueryMapping
    public List<Book> books() {
        return Book.books();
    }
}
