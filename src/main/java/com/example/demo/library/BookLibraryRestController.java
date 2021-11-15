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
public class BookLibraryRestController {
    
    @Autowired
    private BookLibraryRepository repository;

    @GetMapping("")
    public Iterable<BookLibraryResponse> getAllCustomers() {

        StreamSupport.stream(repository.findAll().spliterator(), false);

        List<BookLibraryResponse> response = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(book -> new BookLibraryResponse(book.getTitle(), book.getAuthor(), book.getIsbn()))
                .collect(Collectors.toList());
                
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookLibraryResponse> getCustomerById(@PathVariable Long id) {
        try {
            BookLibraryEntity book = repository.findById(id).get();
            return new ResponseEntity<>(new BookLibraryResponse(book.getTitle(), book.getAuthor(), book.getIsbn()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BookLibraryResponse>(new BookLibraryResponse(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookLibraryEntity createBook(@RequestBody BookLibraryEntity book) {
        return repository.save(book);
    }

    @PutMapping("/{id}")
    public BookLibraryEntity updateCustomer(@PathVariable Long id, @RequestBody BookLibraryEntity book) {
        BookLibraryEntity bookToUpdate = repository.findById(id).get();
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
    public BookLibraryEntity patchCustomer(@PathVariable Long id, @RequestBody BookLibraryEntity book) {
        BookLibraryEntity bookToUpdate = repository.findById(id).get();
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
