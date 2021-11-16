package com.example.demo.library.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.library.transaction.TransactionLibraryEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class UserLibraryEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;
    private String lastName;
    private String email;

    @JsonManagedReference
    @OneToMany(
        mappedBy = "user", 
        cascade = CascadeType.ALL, 
        fetch = javax.persistence.FetchType.EAGER,
        orphanRemoval = true)
            private List<TransactionLibraryEntity> transactions;
    
    public UserLibraryEntity() {
        this.transactions = new java.util.ArrayList<TransactionLibraryEntity>();
    }

    public UserLibraryEntity(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.transactions = new java.util.ArrayList<TransactionLibraryEntity>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TransactionLibraryEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionLibraryEntity> transactions) {
        this.transactions = transactions;
    }

    // public List<BookLibraryEntity> getBooks() {
    //     return books;
    // }

    // public void setBooks(List<BookLibraryEntity> books) {
    //     this.books = books;
    // }

    public void addBook(TransactionLibraryEntity book) {
        this.transactions.add(book);
    }

    @Override
    public String toString() {
        String myTransactions = this.transactions.stream()
            .map(transaction -> transaction.toString())
            .reduce("", (acc, title) -> acc + title + ", ");
        return "LibraryUserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", transactions=" + myTransactions +
                '}';

    }
}
