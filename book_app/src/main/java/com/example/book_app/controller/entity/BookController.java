package com.example.book_app.controller.entity;

import com.example.book_app.dto.ApiResultsDto;
import com.example.book_app.dto.BookDto;
import com.example.book_app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getAll")
    public List<BookDto> getAll() {
        return bookService.getAllBooks();
    }

    @GetMapping("/getResults/{title}/{surname}")
    public ApiResultsDto getResultsTemp(@PathVariable String title,
                                        @PathVariable String surname) {
        return bookService.getApiResultsForBook(title, surname);
    }

    @GetMapping("/saveBook/{title}/{surname}/{id}")
    public void saveBook(@PathVariable String title,
                         @PathVariable String surname,
                         @PathVariable Long userId) {
        bookService.saveBook(getResultsTemp(title, surname), userId);
    }

    @GetMapping("/getBooksByAuthor/{id}")
    public List<BookDto> getBooksByAuthor(@PathVariable Long id) {
        return bookService.findBooksByAuthor(id);
    }

    @GetMapping("/getBooksByUser/{id}")
    public List<BookDto> getBooksByUser(@PathVariable Long id) {
        return bookService.getBooksOwnedByUser(id);
    }

}
