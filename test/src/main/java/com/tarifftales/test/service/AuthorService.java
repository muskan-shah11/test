package com.tarifftales.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tarifftales.test.dto.AuthorDTO;
import com.tarifftales.test.dto.BookDTO;
import com.tarifftales.test.entity.Author;
import com.tarifftales.test.entity.Book;
import com.tarifftales.test.repository.AuthorRepository;

@Service
@Transactional
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author saveAuthorWithBooks(AuthorDTO author1) {
    	Author author =  new Author();
    	List<Book> book = new ArrayList<>();
    	author.setName(author1.getName());
    	for(BookDTO b: author1.getBooks() ) {
       Book bk = new Book();
       bk.setTitle(b.getTitle());
       book.add(bk);
    	}
    	author.setName(author1.getName());
    	author.setBooks(book);
        return authorRepository.save(author);
    }

    // Other service methods...

}
