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
    private String content;
    //private User author;
    //private Book reviewed_book;
}
