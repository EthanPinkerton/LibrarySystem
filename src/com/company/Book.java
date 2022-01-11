package com.company;

public class Book {

    String bookTitle;
    String ISBN;
    String author;
    String genre;

    public Book(String bookTitle, String ISBN, String author, String genre) {
        this.bookTitle = bookTitle;
        this.ISBN = ISBN;
        this.author = author;
        this.genre = genre;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
