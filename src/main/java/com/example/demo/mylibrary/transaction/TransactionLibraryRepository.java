package com.example.demo.mylibrary.transaction;

import org.springframework.data.repository.CrudRepository;

public interface TransactionLibraryRepository extends CrudRepository<TransactionLibraryEntity, Long> {
    TransactionLibraryEntity findById(long id);

}