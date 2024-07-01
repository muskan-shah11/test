package com.tarifftales.test.dto;

import java.util.List;

import lombok.Data;

@Data
public class AuthorDTO {

  //  private Long authorId;
    private String name;
    private List<BookDTO> books;

    // Constructors, getters, setters, and other methods...

}
