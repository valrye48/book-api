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
    @ManyToMany
    private List<Book> books;

    public Author(String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
