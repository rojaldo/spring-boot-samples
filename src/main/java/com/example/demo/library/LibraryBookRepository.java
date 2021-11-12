package com.example.demo.library;

import org.springframework.data.repository.CrudRepository;

public interface LibraryBookRepository extends CrudRepository<LibraryBookEntity, Long> {
    LibraryBookEntity findById(long id);

}