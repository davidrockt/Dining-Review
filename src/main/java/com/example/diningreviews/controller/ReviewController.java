package com.example.diningreviews.controller;

import com.example.diningreviews.model.Review;
import com.example.diningreviews.repository.ReviewRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/reviews")
@RestController
public class ReviewController {
    public final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping
    public Iterable<Review> getRestaurants() {
        return reviewRepository.findAll();
    }
}
