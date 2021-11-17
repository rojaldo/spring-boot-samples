package com.example.demo.pubs;

import org.springframework.data.repository.CrudRepository;

public interface PubRepository extends CrudRepository<PubEntity, Long>{
    PubEntity findById(long id);
}
