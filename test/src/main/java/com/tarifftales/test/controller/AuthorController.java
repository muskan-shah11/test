package com.tarifftales.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarifftales.test.dto.AuthorDTO;
import com.tarifftales.test.entity.Author;
import com.tarifftales.test.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/saveAuthor")
    public Author saveAuthorWithBooks(@RequestBody AuthorDTO author) {
        return authorService.saveAuthorWithBooks(author);
    }

    // Other controller methods...
}
