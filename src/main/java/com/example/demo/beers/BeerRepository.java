package com.example.demo.beers;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BeerRepository extends CrudRepository<BeerEntity, Long> {
  List<BeerEntity> findByAbv(int abv);
  BeerEntity findById(long id);
}
