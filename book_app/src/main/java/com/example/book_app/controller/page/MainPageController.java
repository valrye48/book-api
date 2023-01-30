package com.example.book_app.controller.page;

import com.example.book_app.dto.ApiResultsDto;
import com.example.book_app.dto.BookDto;
import com.example.book_app.dto.NewBookDto;
import com.example.book_app.entity.User;
import com.example.book_app.repository.BookRepository;
import com.example.book_app.repository.UserRepository;
import com.example.book_app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/mainPage")
    public String mainPage(Model model, @SessionAttribute User user) {
        model.addAttribute("newBook", new NewBookDto());
        model.addAttribute("removedBook", new BookDto());
        var userDB = userRepository.findByUsername(user.getUsername());
        List<BookDto> books = bookService.getBooksOwnedByUser(userDB.getId());
        model.addAttribute("books", books);
        return "mainPage";
    }

    @PostMapping("/mainPage")
    public String addBook(@ModelAttribute NewBookDto newBookDto, Model model, @SessionAttribute("user") User user) {
        model.addAttribute("newBook", newBookDto);
        ApiResultsDto apiRes = bookService.getApiResultsForBook(newBookDto.getTitle(), newBookDto.getAuthor());
        if (apiRes.getItems() != null) {
            try {
                var userDB = userRepository.findByUsername(user.getUsername());
                bookService.saveBook(apiRes, userDB.getId());
                model.addAttribute("bookSaved", true);
            } catch (RuntimeException e) {
                model.addAttribute("error", e.getMessage());
            }
        }
        return "mainPage";
    }

    /*@DeleteMapping("/mainPage")
    public String removeBook(@ModelAttribute BookDto removedBook, Model model, @SessionAttribute("user") User user) {
        model.addAttribute("removedBook", removedBook);
        bookService.removeBookFromUser(bookRepository.findByTitle(removedBook.getTitle()).getId(), user.getId());
        return "mainPage";
    }*/
}
