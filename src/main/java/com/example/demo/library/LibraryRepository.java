package com.example.demo.library;

import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<LibraryEntity, Long> {
    LibraryEntity findById(long id);

}