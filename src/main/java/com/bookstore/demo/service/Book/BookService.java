package com.bookstore.demo.service.Book;

import java.util.List;

import com.bookstore.demo.model.Book;

public interface BookService {
    Book create(Book book);

    boolean delete(String id);

    List<Book> getAll();

    List<Book> getByCriteria(String criteria , String search);
    
    Book getById( String id);

    Boolean update(Book book);
} 