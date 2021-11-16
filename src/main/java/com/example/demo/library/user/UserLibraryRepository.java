package com.example.demo.library.user;

import org.springframework.data.repository.CrudRepository;

public interface UserLibraryRepository extends CrudRepository<UserLibraryEntity, Long> {
    UserLibraryEntity findById(long id);

}