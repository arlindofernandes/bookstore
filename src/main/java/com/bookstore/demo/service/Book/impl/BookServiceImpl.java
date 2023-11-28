package com.bookstore.demo.service.Book.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.demo.model.Book;
import com.bookstore.demo.repositories.BookRepository;
import com.bookstore.demo.service.Book.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public boolean delete(String id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getByCriteria(String criteria, String search) {
        Query query = new Query();
        query.addCriteria(Criteria.where(criteria).regex(String.format(search, ".*%s.*"), "i"));
        List<Book> books = mongoTemplate.find(query, Book.class);
        return books;
    }

    @Override
    public Book getById(String id) {

        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            return null;
        }

    }

    @Override
    public Boolean update(Book book) {
        Optional<Book> bookmg = bookRepository.findById(book.getId());
        if (bookmg.isPresent()) {
            bookRepository.save(book);
            return true;
        } else {
            return false;
        }
    }

}
