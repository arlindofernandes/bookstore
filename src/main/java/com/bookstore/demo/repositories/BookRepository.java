package com.bookstore.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bookstore.demo.model.Book;

public interface BookRepository  extends MongoRepository<Book, String>{

    
} 