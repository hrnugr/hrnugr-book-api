package com.harunugur.controller;

import com.harunugur.entity.Book;
import com.harunugur.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public Page<Book> getAllBooks(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "2") int size,
                                  @RequestParam(defaultValue = "id,desc") String[] sort) {

        return bookService.getAllBooks(page, size, sort);

    }
}
