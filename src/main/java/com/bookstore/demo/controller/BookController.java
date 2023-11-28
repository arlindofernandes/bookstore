package com.bookstore.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.demo.model.Book;
import com.bookstore.demo.service.Book.impl.BookServiceImpl;

@RestController
@RequestMapping("/bookstore")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("books")
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok().body(bookService.getAll());
    }

    @RequestMapping(path = "/books/{criteria}/{search}", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getByCriteria(@PathVariable String criteria, @PathVariable String search) {
        List<Book> books = bookService.getByCriteria(criteria, search);
        if (books == null || books.isEmpty()) {
            return ResponseEntity.ok().body(null);
        } else {
            return ResponseEntity.ok().body(books);
        }
    }

    @GetMapping("books/{id}")
    public ResponseEntity<Book> getById(@PathVariable String id) {
        Book book = bookService.getById(id);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {

            return ResponseEntity.ok().body(book);
        }

    }

    @PostMapping("/books")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        return ResponseEntity.ok().body(bookService.create(book));
    }

    @PutMapping("/books")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@RequestBody Book book) {
        Boolean ok = bookService.update(book);
        if (!ok) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Boolean ok = bookService.delete(id);
        if (!ok) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
