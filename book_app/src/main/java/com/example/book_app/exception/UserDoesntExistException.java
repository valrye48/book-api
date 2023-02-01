package com.example.book_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="This user doesn't exist.")
public class UserDoesntExistException extends Exception{
}
