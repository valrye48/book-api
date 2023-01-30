package com.example.book_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "A user with that username already exists.")
public class UsernameExistsException extends Exception{
}
