package com.example.demo.library;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionLibraryRequest {

    @JsonProperty("transaction_date")
    private String transactionDate;

    @JsonProperty("status_date")
    private String statusDate;

    @JsonProperty("due_date")
    private String dueDate;

    @JsonProperty("status")
    private String status;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("book_id")
    private String bookId;

    TransactionLibraryRequest() {
    }

    TransactionLibraryRequest(String transactionDate, String statusDate, String dueDate, String status, String userId, String bookId) {
        this.transactionDate = transactionDate;
        this.statusDate = statusDate;
        this.dueDate = dueDate;
        this.status = status;
        this.userId = userId;
        this.bookId = bookId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "TransactionLibraryRequest{" +
                "transactionDate='" + transactionDate + '\'' +
                ", statusDate='" + statusDate + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", status='" + status + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
