package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CustomerRestController {
    
    @Autowired
    private CustomerRepository repository;

    @GetMapping("/customers")
    public Iterable<CustomerEntity> getAllCustomers() {
        return repository.findAll();
    }

    @GetMapping("/customers/{id}")
    public CustomerEntity getCustomerById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PostMapping("/customers")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CustomerEntity createCustomer(@RequestBody CustomerEntity customer) {
        return repository.save(customer);
    }
}
