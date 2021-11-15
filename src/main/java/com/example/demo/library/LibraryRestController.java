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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/library")
public class LibraryRestController {


    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private UserLibraryRepository userRepository;

    @Autowired
    private BookLibraryRepository bookRepository;


    @GetMapping("")
    public Iterable<LibraryEntity> getAllCustomers() {
        return libraryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryEntity> getCustomerById(@PathVariable Long id) {
        try {
            return new ResponseEntity<LibraryEntity>(libraryRepository.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<LibraryEntity>(new LibraryEntity(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public LibraryEntity createCustomer(@RequestBody LibraryEntity customer) {
        return libraryRepository.save(customer);
    }

    @PutMapping("/{id}")
    public LibraryEntity updateCustomer(@PathVariable Long id, @RequestBody LibraryEntity user) {
        LibraryEntity userToUpdate = libraryRepository.findById(id).get();
        userToUpdate.setName(user.getName());
        return libraryRepository.save(userToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCustomer(@PathVariable Long id) {
        try {
            libraryRepository.deleteById(id);
            return new ResponseEntity<Long>(id, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Long>(id, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LibraryEntity> patchCustomer(@PathVariable Long id, 
            @RequestParam(name = "book_id", required = true, defaultValue = "") String bookId) {
        LibraryEntity userToUpdate = libraryRepository.findById(id).get();
        System.out.println("BOOOK_ID: " + bookId);
        if (bookId != null && !bookId.isEmpty()) {
            try {
                BookLibraryEntity book = bookRepository.findById(Long.parseLong(bookId));
                userToUpdate.addBook(book);
                book.setLibrary(userToUpdate);
                bookRepository.save(book);
            } catch (Exception e) {
                System.out.println("Book not found");
                return new ResponseEntity<LibraryEntity>(new LibraryEntity(), HttpStatus.NOT_FOUND);
            }
          
        }

        System.out.println("USER_TO_UPDATE: " + userToUpdate);
        return new ResponseEntity<LibraryEntity>(userToUpdate, HttpStatus.ACCEPTED);
    }
}
