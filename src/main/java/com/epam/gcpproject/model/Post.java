package com.epam.gcpproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private String id;

    private String title;

    private String description;

    private String fullText;

    private String author;
}
