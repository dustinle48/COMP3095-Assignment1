/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to implement User service contains some abstract methods.
*/
package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Ingredient;
import gbc.comp3095.assignment1.models.Recipe;
import gbc.comp3095.assignment1.models.User;
import gbc.comp3095.assignment1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        user.setEmail(user.getEmail());
        user.setUserName(user.getUserName());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        userRepository.save(user);
    }

    public void addCart(User user, Ingredient ingredient) {
        user.getCart().add(ingredient);
        userRepository.save(user);
    }

    public void deleteCart(User user, Ingredient ingredient) {
        user.getCart().remove(ingredient);
        userRepository.save(user);
    }

    public void addFavorite(User user, Recipe recipe) {
        user.getFavorite().add(recipe);
        userRepository.save(user);
    }

    public void deleteFavorite(User user, Recipe recipe) {
        user.getFavorite().remove(recipe);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
