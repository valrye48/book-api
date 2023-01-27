package com.example.book_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String password;
}
