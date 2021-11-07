/*
Project: Cookbook Forum
        * Assignment: 1
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Nov 6th 2021
        * Description: This file is to set controller of User.
*/
package gbc.comp3095.assignment1.controllers;

import gbc.comp3095.assignment1.models.User;
import gbc.comp3095.assignment1.repositories.MealRepository;
import gbc.comp3095.assignment1.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import gbc.comp3095.assignment1.repositories.UserRepository;

import java.security.Principal;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final MealRepository mealRepository;

    public UserController(UserRepository userRepository, RecipeRepository recipeRepository, MealRepository mealRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.mealRepository = mealRepository;
    }

    @GetMapping("/")
    public String getIndex(Model model, Principal principal) {
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "/index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/users";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("recipes", recipeRepository.findByUsername(user.getUserName()));
        model.addAttribute("meals", mealRepository.findByUsername(user.getUserName()));

        return "users/profile";
    }
}
