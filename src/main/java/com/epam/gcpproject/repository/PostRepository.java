package com.epam.gcpproject.repository;

import com.epam.gcpproject.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
