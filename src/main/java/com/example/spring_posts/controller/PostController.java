package com.example.spring_posts.controller;

import com.example.spring_posts.model.Post;
import com.example.spring_posts.service.PostService;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    PostService postService = new PostService();

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

    @PostMapping("/create-payment-intent")
    public Map<String, Object> createPaymentIntent(@RequestBody Map<String, Object> paymentData) {
        Stripe.apiKey = "sk_test_51PlEdJBsNoGKJEE7P1epIfBsJAXbYFV3g0MNCdA61wDiFAIskZhsGKn5dP18vuHDk0M9S7FTx0v2OjXbn46IhvYv00NWtGcbJW"; // secret key

        Map<String, Object> response = new HashMap<>();
        try {
            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setAmount((Long) paymentData.get("amount"))
                            .setCurrency((String) paymentData.get("currency"))
                            .setPaymentMethod((String) paymentData.get("paymentMethodId"))
                            .setConfirm(true)
                            .build();

            PaymentIntent intent = PaymentIntent.create(params);
            response.put("success", true);
            response.put("intent", intent);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }
        return response;
    }

}
