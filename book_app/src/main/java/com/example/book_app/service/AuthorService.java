package com.example.book_app.service;

import com.example.book_app.dto.AuthorDto;
import com.example.book_app.entity.Author;
import com.example.book_app.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorDto> getAllAuthors() {
        var authors =authorRepository.findAll();
        List<AuthorDto> result = new ArrayList<>();
        for (Author author : authors) {
            result.add(new AuthorDto(author.getId(), author.getName(), author.getSurname()));
        }
        return result;
    }
}
