package com.sumit.h2db.operation.H2WithTableJoinInDetails.controller;

import com.sumit.h2db.operation.H2WithTableJoinInDetails.entity.Author;
import com.sumit.h2db.operation.H2WithTableJoinInDetails.entity.Book;
import com.sumit.h2db.operation.H2WithTableJoinInDetails.service.AuthorBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

  private final AuthorBookService authorBookService;

  public AuthorController(AuthorBookService authorBookService) {
    this.authorBookService = authorBookService;
  }

  @PostMapping("/saveAuthor")
  public Author saveAuthor(@RequestBody Author author) {
    return authorBookService.saveAuthor(author);
  }

  @GetMapping("/getAuthor/{id}")
  public Author getAuthor(@PathVariable("id") int id) {
    return authorBookService.getAuthor(id);
  }

  @GetMapping("/test-cascade")
  public Author testCascade() {
    return authorBookService.testCasCade();
  }

  //    @PutMapping("/updateBook")
  //    public Author updateAuthor(@RequestBody Book book){
  //
  //    }

}
