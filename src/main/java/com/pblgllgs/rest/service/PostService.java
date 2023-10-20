package com.pblgllgs.rest.service;

import com.pblgllgs.rest.entity.Post;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PostService {
    private static final String BASE_URL = "/posts";
    private static final String SELECTOR = "/{id}";
    private final RestClient restClient;

    PostService() {
        restClient = RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();
    }

    public List<Post> findAll() {
        return restClient
                .get()
                .uri(BASE_URL).retrieve()
                .body(new ParameterizedTypeReference<List<Post>>() {
                });
    }

    public Post findById(Integer id) {
        return restClient
                .get()
                .uri(BASE_URL + SELECTOR, id).retrieve()
                .body(new ParameterizedTypeReference<Post>() {
                });
    }

    public Post save(Post post) {
        return restClient
                .post()
                .uri(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    public Post update(Integer id, Post post) {
        return restClient
                .put()
                .uri(BASE_URL + SELECTOR, id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    public Post delete(Integer id) {
        return restClient
                .put()
                .uri(BASE_URL + SELECTOR, id)
                .retrieve()
                .body(Post.class);
    }
}
