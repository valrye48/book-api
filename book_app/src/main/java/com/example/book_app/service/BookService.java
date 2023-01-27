package com.example.book_app.service;

import com.example.book_app.dto.ApiResultsDto;
import com.example.book_app.dto.BookAndUsersDto;
import com.example.book_app.dto.BookDto;
import com.example.book_app.entity.Book;
import com.example.book_app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@PropertySource("classpath:application.properties")
public class BookService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private BookRepository bookRepository;

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.base.url}")
    private String baseUrl;

    public List<BookDto> getAllBooks() {
        var books = bookRepository.findAll();
        List<BookDto> result = new ArrayList<>();
        for (Book book : books) {
            result.add(new BookDto(book.getId(), book.getTitle(), book.getPublished_date().toString(),
                    book.getPage_count(), book.getAverage_rating(), book.getLanguage(),
                    book.getDescription()));
        }
        return result;
    }

    public ApiResultsDto getApiResultsForBook(String title, String authorSurname) {

        //https://www.googleapis.com/books/v1/volumes
        // ?q=flowers+inauthor:keyes
        // &key=AIzaSyDEEFloyM12gslwkcxzd05g09tDCZVwH-s

        String url = baseUrl + "?q=" + title + "+inauthor:" + authorSurname + "&key=" + apiKey;
        return restTemplate.getForObject(url, ApiResultsDto.class);
    }

    public void saveBook(ApiResultsDto apiResultsDto) {
        //TODO:function that gets stuff from apiresults object and parses it to a book object + saves author list in author db

        BookAndUsersDto bookResult = apiResultsDto.getItems().get(0).getVolumeInfo();

        Book book = new Book(bookResult.getTitle(),
                bookResult.getPublished_date(),
                bookResult.getPage_count(),
                bookResult.getAverage_rating(),
                bookResult.getLanguage(),
                bookResult.getDescription());

        bookRepository.save(book);
    }
}
