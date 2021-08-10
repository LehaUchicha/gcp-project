package com.epam.gcpproject.model;

import lombok.Data;

@Data
public class Post {

    private String id;

    private String title;

    private String description;

    private String fullText;

    private String author;
}
