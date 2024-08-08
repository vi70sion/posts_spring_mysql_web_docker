package com.example.spring_posts.controller;

import com.example.spring_posts.model.Post;
import com.example.spring_posts.service.PostService;
import com.example.spring_posts.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    PostService postService = new PostService();

    @Autowired
    private StripeService stripeService;

    //@CrossOrigin
    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    //@CrossOrigin
    @PostMapping("/posts")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        Post newPost = postService.addPost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/posts/page/{page}")
    public ResponseEntity<List<Post>> getPostsPage(@PathVariable int page) {
        return new ResponseEntity<>(postService.getPostsPage(page), HttpStatus.OK);
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String, String>> createCheckoutSession(@RequestBody Map<String, Object> data) {
        try {
            String successUrl = data.get("successUrl").toString();
            String cancelUrl = data.get("cancelUrl").toString();
            Long amount = Long.parseLong(data.get("amount").toString());
            String currency = data.get("currency").toString();

            Session session = stripeService.createCheckoutSession(successUrl, cancelUrl, amount, currency);

            Map<String, String> responseData = new HashMap<>();
            responseData.put("id", session.getId());

            return ResponseEntity.ok(responseData);
        } catch (StripeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}
