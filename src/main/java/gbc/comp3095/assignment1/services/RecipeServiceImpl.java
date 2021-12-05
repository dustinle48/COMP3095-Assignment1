/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to implement Recipe service contains some abstract methods.
*/
package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Recipe;
import gbc.comp3095.assignment1.models.User;
import gbc.comp3095.assignment1.repositories.RecipeRepository;
import gbc.comp3095.assignment1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;

    public void save(Recipe recipe, User user) {
        recipe.setUser(user);
        recipeRepository.save(recipe);
    }

    public Set<Recipe> listAll(String keyword) {
        if (keyword != null) {
            return recipeRepository.search(keyword);
        }
        Set<Recipe> r = new HashSet<>(recipeRepository.findAll());
        return r;
    }

    @Override
    public Recipe findByName(String name) {
        return recipeRepository.findByName(name);
    }
}
