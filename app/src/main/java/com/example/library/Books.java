package com.example.library;

import org.jetbrains.annotations.Contract;

public class Books {
    private int book_id;
    private String book_name;
    private String book_image;
    private String category;
    private String author;


    public Books() {

    }

    public Books(int book_id, String book_name, String books_image, String category, String author) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_image = books_image;
        this.category = category;
        this.author = author;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_image() {
        return book_image;
    }

    public void setBook_image(String book_image) {
        this.book_image = book_image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
