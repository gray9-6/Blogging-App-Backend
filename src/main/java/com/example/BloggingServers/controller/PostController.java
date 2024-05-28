package com.example.BloggingServers.controller;

import com.example.BloggingServers.entity.Post;
import com.example.BloggingServers.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
//@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/savePost")
    public ResponseEntity<?> createPost(@RequestBody Post post){
        try {
            Post createdPost = postService.savePost(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getPosts")
    public ResponseEntity<List<Post>> getAllPost(){
        try {
            return new ResponseEntity<>(postService.getAllPost(),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
