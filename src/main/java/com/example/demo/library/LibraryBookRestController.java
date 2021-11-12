package com.example.demo.library;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
@RequestMapping("/api/v1/library/books")
public class LibraryBookRestController {
    
    @Autowired
    private LibraryBookRepository repository;

    @GetMapping("")
    public Iterable<LibraryBookResponse> getAllCustomers() {

        StreamSupport.stream(repository.findAll().spliterator(), false);

        List<LibraryBookResponse> response = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(book -> new LibraryBookResponse(book.getTitle(), book.getAuthor(), book.getIsbn(), book.isAvailable()))
                .collect(Collectors.toList());
                
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryBookResponse> getCustomerById(@PathVariable Long id) {
        try {
            LibraryBookEntity book = repository.findById(id).get();
            return new ResponseEntity<>(new LibraryBookResponse(book.getTitle(), book.getAuthor(), book.getIsbn(), book.isAvailable()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<LibraryBookResponse>(new LibraryBookResponse(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public LibraryBookEntity createCustomer(@RequestBody LibraryBookEntity customer) {
        return repository.save(customer);
    }

    @PutMapping("/{id}")
    public LibraryBookEntity updateCustomer(@PathVariable Long id, @RequestBody LibraryBookEntity book) {
        LibraryBookEntity bookToUpdate = repository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setIsbn(book.getIsbn());
        bookToUpdate.setDescription(book.getDescription());
        return repository.save(bookToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCustomer(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<Long>(id, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println("Error deleting book: " + e.getMessage());
            return new ResponseEntity<Long>(id, HttpStatus.NOT_FOUND);
        } 
    }

    @PatchMapping("/{id}")
    public LibraryBookEntity patchCustomer(@PathVariable Long id, @RequestBody LibraryBookEntity book) {
        LibraryBookEntity bookToUpdate = repository.findById(id).get();
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
