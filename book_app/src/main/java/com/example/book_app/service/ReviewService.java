package com.example.book_app.service;

import com.example.book_app.dto.ReviewDto;
import com.example.book_app.entity.Review;
import com.example.book_app.repository.BookRepository;
import com.example.book_app.repository.ReviewRepository;
import com.example.book_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ReviewDto> getAll() {
        var reviews = reviewRepository.findAll();
        List<ReviewDto> result = new ArrayList<>();
        for (Review review : reviews) {
            result.add(new ReviewDto(review.getId(),
                    review.getPublished_date(),
                    review.getRating(),
                    review.getContent()));
        }
        return result;
    }

    public void addAReview(ReviewDto reviewDto, Long authorID, Long reviewed_bookID) {
        var user = userRepository.findById(authorID).orElse(null);
        var book = bookRepository.findById(reviewed_bookID).orElse(null);
        Review review = new Review(reviewDto.getPublished_date(),
                reviewDto.getRating(), reviewDto.getContent(),
                user,
                book);

        reviewRepository.save(review);

        if (book.getReviews() != null) {
            book.getReviews().add(review);
        } else {
            List<Review> reviews = new ArrayList<>();
            reviews.add(review);
            book.setReviews(reviews);
        }

        bookRepository.save(book);

        if (user.getReviews() != null) {
            user.getReviews().add(review);
        } else {
            List<Review> reviews = new ArrayList<>();
            reviews.add(review);
            user.setReviews(reviews);
        }

        userRepository.save(user);

    }
}
