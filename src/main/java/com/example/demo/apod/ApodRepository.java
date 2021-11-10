package com.example.demo.apod;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ApodRepository extends CrudRepository<ApodEntity, Long> {
  List<ApodEntity> findByDate(String date);
  ApodEntity findById(long id);
}
