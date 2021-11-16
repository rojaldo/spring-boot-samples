package com.example.demo.library.book;

import org.springframework.data.repository.CrudRepository;

public interface BookLibraryRepository extends CrudRepository<BookLibraryEntity, Long> {
    BookLibraryEntity findById(long id);

}