package com.example.demo.library;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class LibraryEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonManagedReference
    @OneToMany(
        mappedBy = "library", 
        cascade = CascadeType.ALL, 
        fetch = javax.persistence.FetchType.EAGER,
        orphanRemoval = true)
            private List<BookLibraryEntity> books;
    
    
    public LibraryEntity() {
        this.books = new java.util.ArrayList<BookLibraryEntity>();
    }

    public LibraryEntity(String name, String lastName, String email) {
        this.name = name;
        this.books = new java.util.ArrayList<BookLibraryEntity>();
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

    public List<BookLibraryEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookLibraryEntity> books) {
        this.books = books;
    }

    public void addBook(BookLibraryEntity book) {
        this.books.add(book);
    }

    @Override
    public String toString() {
        String myBooks = this.books.stream()
            .map(book -> book.getTitle())
            .reduce("", (acc, title) -> acc + title + ", ");
        return "LibraryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + myBooks +
                '}';

    }
}
