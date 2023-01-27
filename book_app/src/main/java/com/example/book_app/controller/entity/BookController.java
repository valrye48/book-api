package com.example.book_app.controller.entity;

import com.example.book_app.dto.ApiResultsDto;
import com.example.book_app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getResults/{title}/{surname}")
    public ApiResultsDto getResultsTemp(@PathVariable String title,
                                        @PathVariable String surname) {
        return bookService.getApiResultsForBook(title, surname);
    }

}
