package com.example.demo.library.book;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.library.transaction.TransactionLibraryEntity;


@Entity
public class BookLibraryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String author;
    private String isbn;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @OneToMany(
        mappedBy = "book", 
        cascade = CascadeType.ALL, 
        fetch = javax.persistence.FetchType.LAZY,
        orphanRemoval = true)
            private List<TransactionLibraryEntity> transactions;

    public BookLibraryEntity() {
        this.transactions = new java.util.ArrayList<TransactionLibraryEntity>();
    }

    public BookLibraryEntity(String title, String author, String isbn, String description) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
        this.transactions = new java.util.ArrayList<TransactionLibraryEntity>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TransactionLibraryEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionLibraryEntity> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        String myTransactions = this.transactions.stream()
        .map(transaction -> transaction.toString())
        .reduce("", (acc, title) -> acc + title + ", ");

        return  "BookLibraryEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", description='" + description + '\'' +
                ", transactions=" + myTransactions +
                '}';
    }
}
