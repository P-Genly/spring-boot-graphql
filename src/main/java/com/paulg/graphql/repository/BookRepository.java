package com.paulg.graphql.repository;

import com.paulg.graphql.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    Book findBookById(final Long id);
}
