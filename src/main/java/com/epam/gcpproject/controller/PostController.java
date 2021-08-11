package com.epam.gcpproject.controller;

import com.epam.gcpproject.model.Post;
import com.epam.gcpproject.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) {
        return postRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post post) {
        postRepository.findById(id)
                .map(existsPost -> {
                    existsPost.setAuthor(post.getAuthor());
                    existsPost.setDescription(post.getDescription());
                    existsPost.setTitle(post.getTitle());
                    existsPost.setFullText(post.getFullText());
                    return postRepository.save(existsPost);
                })
                .orElseThrow(() -> new RuntimeException("SUch post doesn't exists"));
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id) {
        postRepository.deleteById(id);
    }
}
