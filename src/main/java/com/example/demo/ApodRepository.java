package com.example.demo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ApodRepository extends CrudRepository<Apod, Long> {
  List<Customer> findByDate(String date);
  Customer findById(long id);
}
