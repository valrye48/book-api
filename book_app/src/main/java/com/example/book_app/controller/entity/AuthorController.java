package com.example.book_app.controller.entity;

import com.example.book_app.dto.AuthorDto;
import com.example.book_app.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/getAll")
    public List<AuthorDto> getAll() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/getById/{id}")
    public AuthorDto getById(@PathVariable Long id) {
        return authorService.getById(id);
    }
}
