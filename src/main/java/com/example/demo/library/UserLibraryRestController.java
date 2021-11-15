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
@RequestMapping("/api/v1/library/users")
public class UserLibraryRestController {

    @Autowired
    private UserLibraryRepository userRepository;

    @Autowired
    private BookLibraryRepository bookRepository;


    @GetMapping("")
    public Iterable<UserLibraryEntity> getAllCustomers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserLibraryEntity> getCustomerById(@PathVariable Long id) {
        try {
            return new ResponseEntity<UserLibraryEntity>(userRepository.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<UserLibraryEntity>(new UserLibraryEntity(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserLibraryEntity createCustomer(@RequestBody UserLibraryEntity customer) {
        return userRepository.save(customer);
    }

    @PutMapping("/{id}")
    public UserLibraryEntity updateCustomer(@PathVariable Long id, @RequestBody UserLibraryEntity user) {
        UserLibraryEntity userToUpdate = userRepository.findById(id).get();
        userToUpdate.setName(user.getName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        return userRepository.save(userToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCustomer(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<Long>(id, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Long>(id, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserLibraryEntity> patchCustomer(@PathVariable Long id, 
            @RequestParam(name = "book_id", required = true, defaultValue = "") String bookId) {
        UserLibraryEntity userToUpdate = userRepository.findById(id).get();
        System.out.println("BOOOK_ID: " + bookId);
        if (bookId != null && !bookId.isEmpty()) {
            try {
                BookLibraryEntity book = bookRepository.findById(Long.parseLong(bookId));
                userToUpdate.addBook(book);
                book.setUser(userToUpdate);
                bookRepository.save(book);
            } catch (Exception e) {
                System.out.println("Book not found");
                return new ResponseEntity<UserLibraryEntity>(new UserLibraryEntity(), HttpStatus.NOT_FOUND);
            }
          
        }

        System.out.println("USER_TO_UPDATE: " + userToUpdate);
        return new ResponseEntity<UserLibraryEntity>(userToUpdate, HttpStatus.ACCEPTED);
    }
}
