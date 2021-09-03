package com.example.diningreviews.controller;

import com.example.diningreviews.model.Restaurant;
import com.example.diningreviews.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

@RequestMapping("/restaurants")
@RestController
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;
    private final Pattern zipCodePattern = Pattern.compile("\\d{5}");

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public Iterable<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        if(restaurantRepository.findByNameAndZipCode(restaurant.getName(), restaurant.geetZipCode()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        };
        return restaurantRepository.save(restaurant);
    }

    @GetMapping("/search")
    public Iterable<Restaurant> searchRestaurants(@RequestParam String zipCode, @RequestParam String allergy) {

    }
    // fetch restaurants that match a given zip code and that also have at least one user-submitted score
    // for a given allergy. I want to see them sorted in descending order.
}
