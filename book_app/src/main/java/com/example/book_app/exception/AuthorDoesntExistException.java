package com.example.book_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This author doesn't exist.")
public class AuthorDoesntExistException extends Exception{
}
