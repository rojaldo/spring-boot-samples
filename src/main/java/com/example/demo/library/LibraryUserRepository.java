package com.example.demo.library;

import org.springframework.data.repository.CrudRepository;

public interface LibraryUserRepository extends CrudRepository<LibraryUserEntity, Long> {
    LibraryUserEntity findById(long id);

}