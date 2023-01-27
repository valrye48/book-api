package com.example.book_app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookAndUsersDto {
    @JsonProperty("title")
    private String title;
    @JsonProperty("publishedDate")
    private String published_date;
    @JsonProperty("pageCount")
    private int page_count;
    @JsonProperty("averageRating")
    private double average_rating;
    @JsonProperty("language")
    private String language;
    @JsonProperty("authors")
    private List<String> authors;
    @JsonProperty("description")
    private String description;
}
