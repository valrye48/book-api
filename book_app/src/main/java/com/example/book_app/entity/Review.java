package com.example.book_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NonNull
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date published_date;
    private double rating;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    private User author;
    @ManyToOne
    private Book reviewed_book;

    public Review(Date published_date, double rating, String content) {
        this.id = id;
        this.published_date = published_date;
        this.rating = rating;
        this.content = content;
    }

    public Review(Date published_date, double rating, String content, User author, Book reviewed_book) {
        this.published_date = published_date;
        this.rating = rating;
        this.content = content;
        this.author = author;
        this.reviewed_book = reviewed_book;
    }
}
