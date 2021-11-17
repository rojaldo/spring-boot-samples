package com.example.demo.purchase;

import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<PurchaseEntity, Long>{
    PurchaseEntity findById(long id);
}
