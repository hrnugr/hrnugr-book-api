package com.harunugur.service;

import com.harunugur.entity.Book;
import com.harunugur.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public Page<Book>  getAllBooks(int page, int size, String[] sort) {

        List<Order> orders = new ArrayList<Order>();

        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(Sort.Direction.fromString(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Order(Sort.Direction.fromString(sort[1]), sort[0]));
        }

        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
        Page<Book> books = repository.findAll(pagingSort);

        return books;
     }

}
