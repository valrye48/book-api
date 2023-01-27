package com.example.book_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private Date published_date;
    private double rating;
    private String content;
}
