package com.paulg.graphql.service.impl;

import com.paulg.graphql.entity.Author;
import com.paulg.graphql.repository.AuthorRepository;
import com.paulg.graphql.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findAuthorById(final Long id) {
        return authorRepository.findAuthorById(id);
    }
}
