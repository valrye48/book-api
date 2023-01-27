package com.example.book_app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiResultsDto {
    private String kind;
    private int totalItems;
    private List<VolumeDto> items;
}
