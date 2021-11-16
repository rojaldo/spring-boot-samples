package com.example.demo.beers;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BeerRepository extends CrudRepository<BeerEntity, Long> {
  List<BeerEntity> findByAbv(int abv);

  List<BeerEntity> findByAbvLessThan(Double abv);

  List<BeerEntity> findByAbvGreaterThan(Double abv);

  List<BeerEntity> findByAbvGreaterThanAndAbvLessThan(Double less, Double great);

  BeerEntity findById(long id);
}
