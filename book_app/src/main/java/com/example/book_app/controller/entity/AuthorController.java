package com.example.book_app.controller.entity;

import com.example.book_app.dto.AuthorDto;
import com.example.book_app.exception.AuthorDoesntExistException;
import com.example.book_app.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public AuthorDto getById(@PathVariable Long id) throws AuthorDoesntExistException {
        return authorService.getById(id);
    }

    @DeleteMapping("/removeAuthor/{id}")
    public void removeAuthor(@PathVariable Long id) throws AuthorDoesntExistException {
        authorService.deleteAuthor(id);
    }
}
