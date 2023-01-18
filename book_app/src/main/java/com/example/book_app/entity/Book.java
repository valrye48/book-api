package com.example.book_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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
    private String title;
    private Date published_date;
    private int page_count;
    private double average_rating;
    private String language;
    @ManyToMany(mappedBy = "books")
    private List<Author> authors;
    private String category;
    private String description;
    @ManyToMany
    private List<User> users;
    @OneToMany(mappedBy = "reviewed_book")
    private List<Review> reviews;
}
