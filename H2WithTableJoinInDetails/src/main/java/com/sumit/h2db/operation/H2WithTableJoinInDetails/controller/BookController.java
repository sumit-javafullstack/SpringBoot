package com.sumit.h2db.operation.H2WithTableJoinInDetails.controller;

import com.sumit.h2db.operation.H2WithTableJoinInDetails.entity.Book;
import com.sumit.h2db.operation.H2WithTableJoinInDetails.service.AuthorBookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    private final AuthorBookService authorBookService;

    public BookController(AuthorBookService authorBookService) {
        this.authorBookService = authorBookService;
    }

    @PostMapping("/saveBook")
    public Book saveBook(@RequestBody Book book){
        return  authorBookService.saveBook(book);
    }

    @GetMapping("/getBook/{id}")
    public Book getBook(@PathVariable("id") int id){
        return  authorBookService.getBook(id);
    }

//    @PutMapping("/updateBook")
//    public Book updateBook(@RequestBody Book book){
//
//    }
}
