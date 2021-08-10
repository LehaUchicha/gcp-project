package com.epam.gcpproject.controller;

import com.epam.gcpproject.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class PostController {

    private List<Post> posts = new ArrayList<>();

    @PostConstruct
    public void init() {
        posts.add(new Post("1", "title", "description", "full", "author"));
       // posts.add(new Post("2", "title", "description", "full", "author"));

    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return posts;
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable String id) {
        return posts.stream().filter(post -> id.equals(post.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @PostMapping("/posts")
    public Post savePost(@RequestBody Post post) {
        post.setId(UUID.randomUUID().toString());
        posts.add(post);
        return post;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable String id) {
        posts.remove(posts.stream().filter(post -> id.equals(post.getId()))
                .findFirst().orElse(null));
    }
}
