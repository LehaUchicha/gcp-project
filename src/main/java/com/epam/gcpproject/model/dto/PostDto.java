package com.epam.gcpproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private Long id;

    @NotBlank
    @Size(min = 5, max = 50, message = "Should be between 5 and 50")
    private String title;

    @NotBlank
    @Size(min = 50, max = 250)
    private String description;

    @NotBlank
    @Size(min = 50, max = 1000)
    private String fullText;

    @NotBlank
    private String author;

    private String temp;
}
