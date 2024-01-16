package com.paulg.graphql.repository;

import com.paulg.graphql.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findAll();

    Author findAuthorById(final Long id);
}
