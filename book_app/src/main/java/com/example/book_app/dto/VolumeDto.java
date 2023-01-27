package com.example.book_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VolumeDto {
    private String id;
    private BookAndUsersDto volumeInfo;
}
