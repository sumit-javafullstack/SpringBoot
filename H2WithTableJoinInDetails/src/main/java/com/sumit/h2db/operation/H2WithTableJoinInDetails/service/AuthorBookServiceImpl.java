package com.sumit.h2db.operation.H2WithTableJoinInDetails.service;

import com.sumit.h2db.operation.H2WithTableJoinInDetails.entity.Author;
import com.sumit.h2db.operation.H2WithTableJoinInDetails.entity.Book;
import com.sumit.h2db.operation.H2WithTableJoinInDetails.repository.AuthorRepository;
import com.sumit.h2db.operation.H2WithTableJoinInDetails.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorBookServiceImpl implements AuthorBookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  public AuthorBookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
  }

  public Author saveAuthor(Author author) {

    return authorRepository.save(author);
  }

  public Author getAuthor(int id) {
    return authorRepository.findById(id).orElse(null);
  }

  public Book saveBook(Book book) {
    return bookRepository.save(book);
  }

  public Book getBook(int id) {
    return bookRepository.findById(id).orElse(null);
  }

  public Author testCasCade() {

    Author author = new Author();
    Book book = new Book();
    List<Book> bookList = new ArrayList<>();
    book.setBookName("SPRING BOOT MASTER");
    book.setBookPrice(456.33);
    book.setPublishDate(LocalDate.now());
    bookList.add(book);

    author.setAuthorName("Rahul Kumar Jha");
    author.setAuthorDob(LocalDate.parse("1996-02-12"));
    author.setAge(55);
    author.setNumOfPublications(26);
    author.setBookList(bookList);

    book.setAuthor(author);

    // This will also save books because of CascadeType.ALL
    return authorRepository.save(author);
  }

  @Transactional
  public Author saveAuthorWithBooks() {
    try {
      Author author = new Author();
      Book book = new Book();
      List<Book> bookList = new ArrayList<>();
      book.setBookName("SPRING BOOT MASTER");
      book.setBookPrice(456.33);
      book.setPublishDate(LocalDate.now());
      bookList.add(book);

      author.setAuthorName("Rahul Kumar Jha");
      author.setAuthorDob(LocalDate.parse("1996-02-12"));
      author.setAge(55);
      author.setNumOfPublications(26);
      author.setBookList(bookList);

      book.setAuthor(author);

      if (true) {
        // Simulate an error
        throw new RuntimeException("Simulated error");
      }

      // This will also save books because of CascadeType.ALL
      return authorRepository.save(author);
    } catch (RuntimeException e) {
      // Transaction will be rolled back automatically due to @Transactional
      e.printStackTrace();
      throw e; // Rethrow the exception to ensure rollback
    }
  }
}
