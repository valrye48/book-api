package com.example.book_app.repository;

import com.example.book_app.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    boolean existsBySurname(String surname);

    Author findBySurname(String surname);
}
