package com.example.diningreviews.repository;

import com.example.diningreviews.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Optional<Restaurant> findByNameAndZipCode(String name, String zipCode);
    Iterable<Restaurant> findByZipCodeAndPeanutScoreNotNullOrderByName(String zipCode);
    Iterable<Restaurant> findByZipCodeAndDairyScoreNotNullOrderByName(String zipCode);
    Iterable<Restaurant> findByZipCodeAndEggScoreNotNullOrderByName(String zipCode);
    // fetch restaurants that match a given zip code and that also have at least one user-submitted score
    // for a given allergy. I want to see them sorted in descending order.
}
