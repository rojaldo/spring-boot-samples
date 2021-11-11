package com.example.demo.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BookRestController {
    
    @Autowired
    private BookRepository repository;

    @GetMapping("/books")
    public Iterable<BookEntity> getAllCustomers() {
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    public BookEntity getCustomerById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PostMapping("/books")
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookEntity createCustomer(@RequestBody BookEntity customer) {
        return repository.save(customer);
    }

    @PutMapping("/books/{id}")
    public BookEntity updateCustomer(@PathVariable Long id, @RequestBody BookEntity book) {
        BookEntity bookToUpdate = repository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setIsbn(book.getIsbn());
        bookToUpdate.setDescription(book.getDescription());
        return repository.save(bookToUpdate);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Long> deleteCustomer(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<Long>(id, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println("Error deleting book: " + e.getMessage());
            return new ResponseEntity<Long>(id, HttpStatus.NOT_FOUND);
        } 
    }

    @PatchMapping("/books/{id}")
    public BookEntity patchCustomer(@PathVariable Long id, @RequestBody BookEntity book) {
        BookEntity bookToUpdate = repository.findById(id).get();
        if(book.getTitle() != null) {
            bookToUpdate.setTitle(book.getTitle());
        }
        if(book.getAuthor() != null) {
            bookToUpdate.setAuthor(book.getAuthor());
        }
        if(book.getIsbn() != null) {
            bookToUpdate.setIsbn(book.getIsbn());
        }
        if(book.getDescription() != null) {
            bookToUpdate.setDescription(book.getDescription());
        }
        return repository.save(bookToUpdate);
    }
}
