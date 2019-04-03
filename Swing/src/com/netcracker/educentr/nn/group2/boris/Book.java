package com.netcracker.educentr.nn.group2.boris;

import java.util.Objects;

/**
 * Created by bola0814 on 12.10.2016.
 */
public class Book {
    private long isbn;
    private String name;
    private Author author;
    private double price;
    private int qty;

    public Book(long isbn, String name, int qty, double price, Author author) {
        this.isbn = isbn;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.author = author;
    }

    public Book(long isbn, String name, Author author) {
        this(isbn, name, 0, 0.0, author);
    }

    public long getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn == book.isbn &&
                Objects.equals(name, book.name) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, name, author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author=" + author +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
