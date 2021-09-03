package com.example.diningreviews.controller;

import com.example.diningreviews.model.Review;
import com.example.diningreviews.model.ReviewStatus;
import com.example.diningreviews.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequestMapping("/admin")
@RestController
public class AdminController {
    public final ReviewRepository reviewRepository;
    public final UserController userController;

    public AdminController(ReviewRepository reviewRepository, UserController userController) {
        this.reviewRepository = reviewRepository;
        this.userController = userController;
    }

    @GetMapping("/reviews")
    public Iterable<Review> getPendingReviews() {
        return reviewRepository.findByStatus(ReviewStatus.PENDING);
    }

    @PutMapping("/reviews/{id}")
    public void rateReview(@PathVariable Long id, @RequestParam Boolean accepted) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Review review = optionalReview.get();
        if (accepted) {
            review.setStatus(ReviewStatus.ACCEPTED);
        } else {
            review.setStatus(ReviewStatus.REJECTED);
        }
    }

    /*
    As an admin, I want to get the list of all dining reviews that are pending approval.
    As an admin, I want to approve or reject a given dining review.
     */
}
