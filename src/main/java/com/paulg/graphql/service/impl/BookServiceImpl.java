package com.paulg.graphql.service.impl;

import com.paulg.graphql.entity.Book;
import com.paulg.graphql.repository.BookRepository;
import com.paulg.graphql.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(final Long id) {
        return bookRepository.findBookById(id);
    }
}
