package com.example.demo.library;

import org.springframework.data.repository.CrudRepository;

public interface TransactionLibraryRepository extends CrudRepository<TransactionLibraryEntity, Long> {
    TransactionLibraryEntity findById(long id);

}