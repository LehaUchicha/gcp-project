package com.epam.gcpproject;

import com.epam.gcpproject.model.Post;
import com.epam.gcpproject.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
class GcpProjectApplicationTests {

    @MockBean
    private PostRepository postRepository;

    @Test
    void contextLoads() {
        Mockito.when(postRepository.findAll()).thenReturn(List.of(new Post()));
    }

}
