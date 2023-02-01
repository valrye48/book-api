package com.example.book_app.service;

import com.example.book_app.dto.AuthorDto;
import com.example.book_app.entity.Author;
import com.example.book_app.entity.Book;
import com.example.book_app.exception.AuthorDoesntExistException;
import com.example.book_app.repository.AuthorRepository;
import com.example.book_app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<AuthorDto> getAllAuthors() {
        var authors =authorRepository.findAll();
        List<AuthorDto> result = new ArrayList<>();
        for (Author author : authors) {
            result.add(new AuthorDto(author.getId(), author.getName(), author.getSurname()));
        }
        return result;
    }

    public AuthorDto getById(Long id) throws AuthorDoesntExistException {
        var author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            throw new AuthorDoesntExistException();
        }
        return new AuthorDto(author.getId(),
                author.getName(),
                author.getSurname());
    }

    public void deleteAuthor(Long id) throws AuthorDoesntExistException {
        var author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            if (!author.getBooks().isEmpty()) {
                for (Book book : author.getBooks()) {
                    book.getAuthors().remove(author);
                    bookRepository.save(book);
                }
            }
            authorRepository.delete(author);
        } else {
            throw new AuthorDoesntExistException();
        }
    }
}
