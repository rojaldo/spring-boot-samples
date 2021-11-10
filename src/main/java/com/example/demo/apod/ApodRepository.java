package com.example.demo.apod;

import java.util.List;

import com.example.demo.customer.Customer;

import org.springframework.data.repository.CrudRepository;

public interface ApodRepository extends CrudRepository<ApodEntity, Long> {
  List<Customer> findByDate(String date);
  Customer findById(long id);
}
