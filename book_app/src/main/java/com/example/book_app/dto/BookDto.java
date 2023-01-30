package com.example.book_app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
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
    @JsonProperty("description")
    private String description;
}
