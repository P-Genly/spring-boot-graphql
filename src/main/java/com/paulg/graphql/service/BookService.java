package com.paulg.graphql.service;

import com.paulg.graphql.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findBookById(final Long id);
}
