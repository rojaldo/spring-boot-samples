package com.example.demo.library;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "LibraryUserEntity_id")
    private UserLibraryEntity user;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "LibraryEntity_id")
    private LibraryEntity library;

    public BookLibraryEntity() {
    }

    public BookLibraryEntity(String title, String author, String isbn, String description) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
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

    public UserLibraryEntity getUser() {
        return user;
    }

    public void setUser(UserLibraryEntity user) {
        this.user = user;
    }

    public LibraryEntity getLibrary() {
        return library;
    }

    public void setLibrary(LibraryEntity library) {
        this.library = library;
    }

    public boolean isAvailable() {
        return user == null;
    }

    @Override
    public String toString() {
        return "LibraryBookEntity [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn
                + ", description=" + description + ", user=" + user + ", library=" + library + "]";
    }
}
