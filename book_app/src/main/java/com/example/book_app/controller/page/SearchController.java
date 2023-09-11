package com.example.book_app.controller.page;

import com.example.book_app.repository.BookRepository;
import com.example.book_app.repository.UserRepository;
import com.example.book_app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SearchController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/mainPage/search/{title}")
    public String getSearchResults(@PathVariable String title, Model model) {
        return "search";
    }
}
