package com.example.book_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Wrong login and/or password.")
public class WrongLoginException extends Exception {
}
