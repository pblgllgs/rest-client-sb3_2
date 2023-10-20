package com.pblgllgs.rest.controller;

import com.pblgllgs.rest.entity.Post;
import com.pblgllgs.rest.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        return new ResponseEntity<>(service.save(post), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> savePost(@PathVariable("id") Integer id, @RequestBody Post post) {
        return new ResponseEntity<>(service.update(id, post), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") Integer id){
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }
}
