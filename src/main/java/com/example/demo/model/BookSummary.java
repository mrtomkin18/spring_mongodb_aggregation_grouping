package com.example.demo.model;

import java.util.List;

public class BookSummary {
    List <Book> books;
    float total_price;
    int total_book;
    String type;


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public int getTotal_book() {
        return total_book;
    }

    public void setTotal_book(int total_book) {
        this.total_book = total_book;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
