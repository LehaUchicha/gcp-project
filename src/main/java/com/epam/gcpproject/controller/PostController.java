package com.epam.gcpproject.controller;

import com.epam.gcpproject.mapper.PostMapper;
import com.epam.gcpproject.model.dto.PostDto;
import com.epam.gcpproject.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Validated
public class PostController {

    private final PostRepository postRepository;

    private final PostMapper mapper;

    @GetMapping("/posts")
    public List<PostDto> getPosts() {
        return postRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id) {
        return postRepository.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/posts")
    public ResponseEntity<PostDto> savePost(@RequestBody @Validated PostDto postDto) {
        return new ResponseEntity<>(mapper.toDto(postRepository.save(mapper.toEntity(postDto))), HttpStatus.CREATED);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable long id, @RequestBody @Validated PostDto postDto) {
        return postRepository.findById(id)
                .map(existsPost -> {
                    existsPost.setAuthor(postDto.getAuthor());
                    existsPost.setDescription(postDto.getDescription());
                    existsPost.setTitle(postDto.getTitle());
                    existsPost.setFullText(postDto.getFullText());
                    return ResponseEntity.ok(mapper.toDto(postRepository.save(existsPost)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
    }
}
