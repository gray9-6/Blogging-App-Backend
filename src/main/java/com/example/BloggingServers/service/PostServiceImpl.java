package com.example.BloggingServers.service;

import com.example.BloggingServers.entity.Post;
import com.example.BloggingServers.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    public Post savePost(Post post){
        post.setLikedCount(0);
        post.setViewCount(0);
        post.setDate(new Date());

        return postRepository.save(post);
    }

    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

}
