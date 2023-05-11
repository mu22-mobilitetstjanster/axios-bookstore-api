package com.example.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
  private long id;
  private String title;
  private String author;
}
