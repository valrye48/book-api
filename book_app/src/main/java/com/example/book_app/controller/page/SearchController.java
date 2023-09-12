package com.example.book_app.controller.page;

import com.example.book_app.dto.ApiResultsDto;
import com.example.book_app.dto.BookAndUsersDto;
import com.example.book_app.dto.VolumeDto;
import com.example.book_app.repository.BookRepository;
import com.example.book_app.repository.UserRepository;
import com.example.book_app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    //TODO: an idea for making the api results more accurate - replace the add book function entirely
    // and make this \/ search for books by title AND author

    //TODO: do this BEFORE making the add book function on the search results page

    @GetMapping("/mainPage/search/{title}")
    public String getSearchResults(@PathVariable String title, Model model) {
        ApiResultsDto resultsDto = bookService.getApiResultsForBookByTitle(title);
        List<VolumeDto> itemsFromResults = resultsDto.getItems();
        List<BookAndUsersDto> booksResult = new ArrayList<>();
        for (VolumeDto volumeDto : itemsFromResults) {
            booksResult.add(volumeDto.getVolumeInfo());
        }
        model.addAttribute("results", booksResult);
        return "search";
    }

}
