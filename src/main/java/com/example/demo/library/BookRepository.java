package com.example.demo.library;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
    BookEntity findById(long id);
    
  }