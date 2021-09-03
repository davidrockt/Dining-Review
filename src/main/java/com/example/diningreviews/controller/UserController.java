package com.example.diningreviews.controller;

import com.example.diningreviews.model.User;
import com.example.diningreviews.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user) {
        // TODO User already exists?
        final User saved = userRepository.save(user);
    }

    @PutMapping("/{displayName}")
    public User editUser(@RequestBody User user, @PathVariable String displayName) {
        // TODO User already exists?
        Optional<User> optionalUser = userRepository.findByDisplayName(displayName);
        if(optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User existingUser = optionalUser.get();
        copyInfoToExistingUser(existingUser, user);
        return userRepository.save(existingUser);
    }

    @GetMapping("/{displayName}")
    public User getUser(@PathVariable String displayName) {
        Optional<User> optionalUser = userRepository.findByDisplayName(displayName);
        if(optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optionalUser.get();
    }

    private void copyInfoToExistingUser(User existingUser, User user) {
        if(!ObjectUtils.isEmpty(user.getCity())) {
            existingUser.setCity(user.getCity());
        }
    }
}
