package com.example.BloggingServers.service;


import com.example.BloggingServers.entity.Post;

import java.util.List;

public interface PostService {

    Post savePost(Post post);
    List<Post> getAllPost();
}
