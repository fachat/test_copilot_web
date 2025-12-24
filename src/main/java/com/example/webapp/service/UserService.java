package com.example.webapp.service;

import com.example.webapp.model.User;
import com.example.webapp.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for User business logic using JakartaEE CDI.
 * Handles user-related operations and business rules.
 */
@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    /**
     * Get all users.
     *
     * @return list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get a user by ID.
     *
     * @param id the user ID
     * @return an Optional containing the user if found
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findByIdOptional(id);
    }

    /**
     * Get a user by username.
     *
     * @param username the username
     * @return an Optional containing the user if found
     */
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Create a new user.
     *
     * @param user the user to create
     * @return the created user
     */
    @Transactional
    public User createUser(User user) {
        userRepository.persist(user);
        return user;
    }

    /**
     * Update an existing user.
     *
     * @param id the user ID
     * @param userDetails the updated user details
     * @return an Optional containing the updated user if found
     */
    @Transactional
    public Optional<User> updateUser(Long id, User userDetails) {
        return userRepository.findByIdOptional(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setEmail(userDetails.getEmail());
                    return user;
                });
    }

    /**
     * Delete a user by ID.
     *
     * @param id the user ID
     * @return true if user was deleted, false otherwise
     */
    @Transactional
    public boolean deleteUser(Long id) {
        return userRepository.deleteById(id);
    }
}
