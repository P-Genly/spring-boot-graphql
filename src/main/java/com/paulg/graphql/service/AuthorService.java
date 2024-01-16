package com.paulg.graphql.service;

import com.paulg.graphql.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    Author findAuthorById(final Long id);
}
