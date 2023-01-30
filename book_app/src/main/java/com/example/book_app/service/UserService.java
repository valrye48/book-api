package com.example.book_app.service;

import com.example.book_app.dto.BookDto;
import com.example.book_app.dto.ReviewDto;
import com.example.book_app.dto.UserDto;
import com.example.book_app.entity.Book;
import com.example.book_app.entity.Review;
import com.example.book_app.entity.User;
import com.example.book_app.repository.BookRepository;
import com.example.book_app.repository.ReviewRepository;
import com.example.book_app.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public boolean verifyUser(String username, String password) {
            var userFromDb = userRepository.findByUsername(username);
            if (userFromDb != null) {
                return userFromDb.getPassword().equals(password);
            }
            return false;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public List<UserDto> getAllUsers() {
        var users = userRepository.findAll();
        List<UserDto> result = new ArrayList<>();
        for (User user : users) {
            result.add(new UserDto(user.getId(), user.getUsername(), user.getName(), user.getSurname(),
                    user.getPassword()));
        }
        return result;
    }

    public void saveUser(UserDto userDto) {
        User user = new User(userDto.getUsername(), userDto.getName(), userDto.getSurname(),
                userDto.getPassword());
        userRepository.save(user);
    }

    public void addBookToUser(Long userId, Long bookId) {
        var userVar = userRepository.findById(userId).orElse(null);
        if (userVar != null) {
            if (bookRepository.existsById(bookId)) {
                var bookVar = bookRepository.findById(bookId).orElse(null);
                if (userVar.getBooks().contains(bookVar)) {
                    throw new RuntimeException("This user already owns this book.");
                } else {
                    if (userVar.getBooks() != null) {
                        userVar.getBooks().add(bookVar);
                    } else {
                        List<Book> books = new ArrayList<>();
                        books.add(bookVar);
                        userVar.setBooks(books);
                    }

                    userRepository.save(userVar);

                    if (bookVar.getUsers() != null) {
                        bookVar.getUsers().add(userVar);
                    } else {
                        List<User> users = new ArrayList<>();
                        users.add(userVar);
                        bookVar.setUsers(users);
                    }

                    bookRepository.save(bookVar);
                }
            } else {
                throw new RuntimeException("This book doesn't exist.");
            }
        } else {
            throw new RuntimeException("This user doesn't exist.");
        }
    }

    public void addReviewsToUser(Long userId, ReviewDto review) {
        var userVar = userRepository.findById(userId).orElse(null);
        var reviewVar = reviewRepository.findById(review.getId()).orElse(null);
        if (userVar != null && reviewVar != null) {

            if (userVar.getReviews() != null) {
                userVar.getReviews().add(reviewVar);
            } else {
                List<Review> reviews = new ArrayList<>();
                reviews.add(reviewVar);
                userVar.setReviews(reviews);
            }

            reviewVar.setAuthor(userVar);
            userRepository.save(userVar);
            reviewRepository.save(reviewVar);
        } else {
            throw new RuntimeException("This user/review doesn't exist.");
        }
    }

}
