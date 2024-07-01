package com.tarifftales.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarifftales.test.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

