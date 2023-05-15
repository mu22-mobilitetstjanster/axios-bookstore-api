package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.util.Noise;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/book/*", produces = "application/json")
public class BookController {

  private List<Book> books = new ArrayList<>(
    Arrays.asList(
      new Book(56464526456l, "How To Talk To Absolutely Anyone", "Mack Rhodos"),
      new Book(3454356345l, "Read people like a book", "Patrick Ring"),
      new Book(5465463452l, "Lord of the Rings", "J.R.R. Tolkien"),
      new Book(4765643542l, "Harry Potter and the Sorcerer's Stone", "J.K. Rowling")
    )
  );


  @GetMapping
  public List<Book> showAll() {
    return this.books;
  }

  @GetMapping("/{id}")
  public Book showOne(@PathVariable long id) {
    return books.stream().findFirst().filter(book -> book.getId() == id).get();
  }

  @GetMapping("/search/{query}")
  public List<Book> searchBook(@PathVariable String query) {
    String searchableQuery = query.toLowerCase();
    return books
            .stream()
            .filter(book -> (book.getAuthor() + " - " + book.getTitle()).toLowerCase().contains(searchableQuery))
            .toList();
  }

  @PostMapping(consumes = "application/json")
  public List<Book> addBook(@RequestBody Book book) {
    book.setId(UUID.randomUUID().getMostSignificantBits());

    this.books.add(book);
    return this.books;
  }

  @DeleteMapping("/{id}")
  public List<Book> deleteBook(@PathVariable long id) {
    this.books = this.books.stream().filter(book -> book.getId() != id).toList();
    return this.books;
  }
}