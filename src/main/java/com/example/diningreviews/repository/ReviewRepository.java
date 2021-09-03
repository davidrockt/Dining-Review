package com.example.diningreviews.repository;

import com.example.diningreviews.model.Review;
import com.example.diningreviews.model.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    Iterable<Review> findByStatus(ReviewStatus status);
}
