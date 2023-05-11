package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.util.Noise;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/book/*", produces = "application/json")
public class BookController {

  private final int API_MAX_NOISE = 4000;
  private List<Book> books = new ArrayList<>(
    Arrays.asList(
      new Book(UUID.randomUUID().getMostSignificantBits(), "How To Talk To Absolutely Anyone", "Mack Rhodos"),
      new Book(UUID.randomUUID().getMostSignificantBits(), "Read people like a book", "Patrick Ring"),
      new Book(UUID.randomUUID().getMostSignificantBits(), "Lord of the Rings", "J.R.R. Tolkien"),
      new Book(UUID.randomUUID().getMostSignificantBits(), "Harry Potter and the Sorcerer's Stone", "J.K. Rowling")
    )
  );


  @GetMapping
  public List<Book> showAll() {
    Noise.random(API_MAX_NOISE);
    return this.books;
  }

  @GetMapping("/{id}")
  public Book showOne(@PathVariable long id) {
    Noise.random(API_MAX_NOISE);
    return books.stream().findFirst().filter(book -> book.getId() == id).get();
  }

  @GetMapping("/search/{query}")
  public List<Book> searchBook(@PathVariable String query) {
    System.out.println(query);
    String searchableQuery = query.toLowerCase();
    return books
            .stream()
            .filter(book -> (book.getAuthor() + " - " + book.getTitle()).toLowerCase().contains(searchableQuery))
            .toList();
  }

  @PostMapping(consumes = "application/json")
  public List<Book> addBook(@RequestBody Book book) {
    Noise.random(API_MAX_NOISE);
    book.setId(UUID.randomUUID().getMostSignificantBits());

    this.books.add(book);
    return this.books;
  }

  @DeleteMapping("/{id}")
  public List<Book> deleteBook(@PathVariable long id) {
    Noise.random(API_MAX_NOISE);
    this.books = this.books.stream().filter(book -> book.getId() != id).toList();
    return this.books;
  }
}