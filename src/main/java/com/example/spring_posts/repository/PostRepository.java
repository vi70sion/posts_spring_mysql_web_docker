package com.example.spring_posts.repository;

import com.example.spring_posts.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    public PostRepository() { }
    List<Post> postList = new ArrayList<>();


    public List<Post> getAllPosts() {


        return postList;
    }


}
