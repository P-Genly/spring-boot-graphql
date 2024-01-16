package com.paulg.graphql.controller;

import com.paulg.graphql.entity.Book;
import com.paulg.graphql.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @QueryMapping
    public Book book(@Argument Long id) {
        return bookService.findBookById(id);
    }

    @QueryMapping
    public List<Book> books() {
        return bookService.findAll();
    }
}
