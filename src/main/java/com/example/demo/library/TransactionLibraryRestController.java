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
@RequestMapping("/api/v1/library/transactions")
public class TransactionLibraryRestController {
    @Autowired
    private TransactionLibraryRepository transactionRepository;

    @Autowired
    private BookLibraryRepository bookRepository;

    @Autowired
    private UserLibraryRepository userRepository;

    @GetMapping("")
    public Iterable<TransactionLibraryEntity> getAllCustomers() {

        StreamSupport.stream(transactionRepository.findAll().spliterator(), false);

        List<TransactionLibraryEntity> response = StreamSupport
                .stream(transactionRepository.findAll().spliterator(), false).map(book -> book)
                .collect(Collectors.toList());

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionLibraryEntity> getCustomerById(@PathVariable Long id) {
        try {
            TransactionLibraryEntity transaction = transactionRepository.findById(id).get();
            return transaction != null ? ResponseEntity.ok(transaction) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<TransactionLibraryEntity>(new TransactionLibraryEntity(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public TransactionLibraryEntity createBook(@RequestBody TransactionLibraryRequest transaction) {
        try {
            UserLibraryEntity user = this.userRepository.findById(Long.valueOf(transaction.getUserId())).get();
            BookLibraryEntity book = this.bookRepository.findById(Long.valueOf(transaction.getBookId())).get();

            TransactionLibraryEntity newTransaction = 
            new TransactionLibraryEntity(
                transaction.getTransactionDate(),
                transaction.getStatusDate(),
                transaction.getDueDate(), 
                transaction.getStatus(), 
                user, book);
                System.out.println("TransactionLibraryEntity: " + newTransaction);
                return transactionRepository.save(newTransaction);
        } catch (Exception e) {
            return new TransactionLibraryEntity();
        }
    }

    @PutMapping("/{id}")
    public TransactionLibraryEntity updateCustomer(@PathVariable Long id, @RequestBody TransactionLibraryEntity book) {
        TransactionLibraryEntity transactionToUpdate = transactionRepository.findById(id).get();
        transactionToUpdate.setTransactionDate(transactionToUpdate.getTransactionDate());
        transactionToUpdate.setStatusDate(transactionToUpdate.getStatusDate());
        transactionToUpdate.setStatus(transactionToUpdate.getStatus());
        transactionToUpdate.setDueDate(transactionToUpdate.getDueDate());

        return transactionRepository.save(transactionToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCustomer(@PathVariable Long id) {
        try {
            transactionRepository.deleteById(id);
            return new ResponseEntity<Long>(id, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println("Error deleting book: " + e.getMessage());
            return new ResponseEntity<Long>(id, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public TransactionLibraryEntity patchCustomer(@PathVariable Long id,
            @RequestBody TransactionLibraryEntity transaction) {
        TransactionLibraryEntity transactionToUpdate = transactionRepository.findById(id).get();
        if (transaction.getTransactionDate() != null) {
            transactionToUpdate.setTransactionDate(transaction.getTransactionDate());
        }
        if (transaction.getStatusDate() != null) {
            transactionToUpdate.setStatusDate(transaction.getStatusDate());
        }
        if (transaction.getStatus() != null) {
            transactionToUpdate.setStatus(transaction.getStatus());
        }
        if (transaction.getDueDate() != null) {
            transactionToUpdate.setDueDate(transaction.getDueDate());
        }
        return transactionRepository.save(transactionToUpdate);
    }
}
