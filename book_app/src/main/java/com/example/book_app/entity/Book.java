package com.example.book_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NonNull
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 600)
    private String title;
    private String published_date;
    private int page_count;
    private double average_rating;
    private String language;
    @ManyToMany(mappedBy = "books")
    private List<Author> authors;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToMany
    private List<User> users;

    public Book(String title, String published_date, int page_count, double average_rating, String language, String description) {
        this.title = title;
        this.published_date = published_date;
        this.page_count = page_count;
        this.average_rating = average_rating;
        this.language = language;
        this.description = description;
    }
}
