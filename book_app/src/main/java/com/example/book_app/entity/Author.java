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
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}
