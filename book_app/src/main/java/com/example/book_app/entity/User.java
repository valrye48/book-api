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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String password;
    @ManyToMany(mappedBy = "users")
    private List<Book> books;
    @OneToMany(mappedBy = "author")
    private List<Review> reviews;
}
