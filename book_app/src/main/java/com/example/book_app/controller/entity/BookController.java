package com.example.book_app.controller.entity;

import com.example.book_app.dto.ApiResultsDto;
import com.example.book_app.dto.BookDto;
import com.example.book_app.exception.BookDoesntExistException;
import com.example.book_app.exception.UserDoesntExistException;
import com.example.book_app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
                         @PathVariable Long userId) throws UserDoesntExistException, BookDoesntExistException {
        bookService.saveBook(getResultsTemp(title, surname), userId);
    }

    @GetMapping("/getBooksByAuthor/{id}")
    public List<BookDto> getBooksByAuthor(@PathVariable Long id) {
        return bookService.findBooksByAuthor(id);
    }

    @GetMapping("/getBooksByUser/{id}")
    public List<BookDto> getBooksByUser(@PathVariable Long id) throws UserDoesntExistException {
        return bookService.getBooksOwnedByUser(id);
    }

    @DeleteMapping("/removeBook/{id}")
    public void deleteBook(@PathVariable Long id) throws BookDoesntExistException {
        bookService.removeBook(id);
    }

    @GetMapping("/getBookById/{id}")
    public BookDto getBookById(@PathVariable Long id) throws BookDoesntExistException { return bookService.findBookById(id); }

    @PatchMapping("/removeBookFromUser/{id}/{userId}")
    public void removeBookFromUser(@PathVariable Long id, @PathVariable Long userId) throws UserDoesntExistException, BookDoesntExistException {
        bookService.removeBookFromUser(id, userId);
    }

}
