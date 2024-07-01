package com.tarifftales.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarifftales.test.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
