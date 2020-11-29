package com.harunugur.repository.book;

import com.harunugur.entity.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByPublished(boolean published, Pageable pageable);
    Page<Book> findByName(String name, Pageable pageable);
    List<Book> findByName(String name, Sort sort);
    Page<Book> findAll(Pageable pageable);

}
