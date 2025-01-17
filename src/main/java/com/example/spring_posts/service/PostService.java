package com.example.spring_posts.service;

import com.example.spring_posts.model.Post;
import com.example.spring_posts.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostService {
    PostRepository postRepository = new PostRepository();
    public List<Post> getAllPosts() {
        return postRepository.getAllPosts();
    }

    public Post addPost(Post post) {
        return postRepository.addPost(post);
    }

    public List<Post> getPostsPage(int page) {
        return postRepository.getPostsPage(page);
    }

}
