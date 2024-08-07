package com.sumit.h2db.operation.H2WithTableJoinInDetails.service;

import com.sumit.h2db.operation.H2WithTableJoinInDetails.entity.Author;
import com.sumit.h2db.operation.H2WithTableJoinInDetails.entity.Book;
import org.springframework.stereotype.Service;


public interface AuthorBookService {

  Author saveAuthor(Author author);

  Author getAuthor(int id);

  Book saveBook(Book book);

  Book getBook(int id);

  public Author testCasCade();
}
