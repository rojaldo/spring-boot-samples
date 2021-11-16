package com.example.demo.library.transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.library.book.BookLibraryEntity;
import com.example.demo.library.user.UserLibraryEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class TransactionLibraryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("transaction_date")
    private String transactionDate;

    @JsonProperty("status_date")
    private String statusDate;

    @JsonProperty("due_date")
    private String dueDate;

    @JsonProperty("status")
    private String status;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "UserLibraryEntity_id")
    private UserLibraryEntity user;

    @ManyToOne()
    // @JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "BookLibraryEntity_id")
    private BookLibraryEntity book;

    TransactionLibraryEntity() {
    }

    TransactionLibraryEntity(String transactionDate, String statusDate, String dueDate, String status) {
        this.transactionDate = transactionDate;
        this.status = statusDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    TransactionLibraryEntity(String transactionDate, String statusDate, String dueDate, String status,
            UserLibraryEntity user, BookLibraryEntity book) {
        this.transactionDate = transactionDate;
        this.status = statusDate;
        this.dueDate = dueDate;
        this.status = status;
        this.user = user;
        this.book = book;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserLibraryEntity getUser() {
        return user;
    }

    public void setUser(UserLibraryEntity user) {
        this.user = user;
    }

    public BookLibraryEntity getBook() {
        return book;
    }

    public void setBook(BookLibraryEntity book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "TransactionLibraryEntity [id=" + id + ", transactionDate=" + transactionDate + ", statusDate="
                + statusDate + ", dueDate=" + dueDate + ", status=" + status + ", user=" + user + ", book=" + book
                + "]";
    }
}
