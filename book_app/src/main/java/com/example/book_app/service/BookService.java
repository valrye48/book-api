package com.example.book_app.service;

import com.example.book_app.dto.ApiResultsDto;
import com.example.book_app.dto.BookAndUsersDto;
import com.example.book_app.dto.BookDto;
import com.example.book_app.entity.Author;
import com.example.book_app.entity.Book;
import com.example.book_app.entity.Review;
import com.example.book_app.entity.User;
import com.example.book_app.repository.AuthorRepository;
import com.example.book_app.repository.BookRepository;
import com.example.book_app.repository.ReviewRepository;
import com.example.book_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@PropertySource("classpath:application.properties")
public class BookService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.base.url}")
    private String baseUrl;

    public List<BookDto> getAllBooks() {
        var books = bookRepository.findAll();
        List<BookDto> result = new ArrayList<>();
        for (Book book : books) {
            result.add(new BookDto(book.getId(), book.getTitle(), book.getPublished_date(),
                    book.getPage_count(), book.getAverage_rating(), book.getLanguage(),
                    book.getDescription()));
        }
        return result;
    }

    public List<BookDto> findBooksByAuthor(Long authorId) {
        var books = bookRepository.findAll();
        List<BookDto> result = new ArrayList<>();
        for (Book book : books) {
            for (Author author : book.getAuthors()) {
                if (Objects.equals(author.getId(), authorId)) {
                    result.add(new BookDto(book.getId(),
                            book.getTitle(),
                            book.getPublished_date(),
                            book.getPage_count(), book.getAverage_rating(), book.getLanguage(),
                            book.getDescription()));
                }
            }
        }
        return result;
    }

    public ApiResultsDto getApiResultsForBook(String title, String authorSurname) {
        String url = baseUrl + "?q=" + title + "+inauthor:" + authorSurname + "&key=" + apiKey;
        return restTemplate.getForObject(url, ApiResultsDto.class);
    }

    public void saveBook(ApiResultsDto apiResultsDto, Long userId) {

        BookAndUsersDto bookResult = apiResultsDto.getItems().get(0).getVolumeInfo();

        Book book = new Book(bookResult.getTitle(),
                bookResult.getPublished_date(),
                bookResult.getPage_count(),
                bookResult.getAverage_rating(),
                bookResult.getLanguage(),
                bookResult.getDescription());

        if (!bookRepository.existsByTitle(book.getTitle())) {

            List<Author> authors = new ArrayList<>();
            for (String authorName : bookResult.getAuthors()) {
                StringBuilder nameSB = new StringBuilder();
                StringBuilder surnameSB = new StringBuilder();
                String authorArr[] = authorName.split(" ");
                for (int i = 0; i < authorArr.length; i++) {
                    if (i > 0) {
                        surnameSB.append(authorArr[i]);
                    } else {
                        nameSB.append(authorArr[i]);
                    }
                }
                String name = nameSB.toString();
                String surname = surnameSB.toString();
                if (!authorRepository.existsBySurname(surname)) {
                    List<Book> books = new ArrayList<>();
                    books.add(book);
                    Author author = new Author(name, surname, books);
                    authors.add(author);
                    authorRepository.save(author);
                } else {
                    var existingAuthor = authorRepository.findBySurname(surname);
                    existingAuthor.getBooks().add(book);
                    authorRepository.save(existingAuthor);
                }
            }

            book.setAuthors(authors);

            bookRepository.save(book);

            var bookDB = bookRepository.findByTitle(book.getTitle());

            userService.addBookToUser(userId, bookDB.getId());

        } else {
            var bookDB = bookRepository.findByTitle(book.getTitle());
            userService.addBookToUser(userId, bookDB.getId());
            bookRepository.save(bookDB);
        }
    }

    public List<BookDto> getBooksOwnedByUser(Long userId) {
        var user = userRepository.findById(userId).orElse(null);
        var books = user.getBooks();
        List<BookDto> result = new ArrayList<>();
        if (books != null) {
            for (Book book : books) {
                result.add(new BookDto(book.getId(),
                        book.getTitle(),
                        book.getPublished_date(),
                        book.getPage_count(), book.getAverage_rating(),
                        book.getLanguage(), book.getDescription()));
            }
        }
        return result;
    }

    public void removeBook(Long bookId) {
        var book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            var users = book.getUsers();
            if (users != null) {
                for (User user : users) {
                    user.getBooks().remove(book);
                }
            }
            var authors = book.getAuthors();
            if (authors != null) {
                for (Author author : authors) {
                    author.getBooks().remove(book);
                }
            }
            var reviews = book.getReviews();
            if (reviews != null) {
                for (Review review : reviews) {
                    reviewRepository.delete(review);
                }
            }
            bookRepository.delete(book);
        }
    }

    public void removeBookFromUser(Long bookId, Long userId) {
        var book = bookRepository.findById(bookId).orElse(null);
        var user = userRepository.findById(userId).orElse(null);
        if (book != null && user != null) {
            if (user.getBooks().contains(book)) {
                user.getBooks().remove(book);
            }
            bookRepository.delete(book);
        }
    }

}
