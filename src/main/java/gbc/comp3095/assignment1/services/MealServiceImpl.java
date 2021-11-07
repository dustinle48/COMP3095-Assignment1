/*
Project: Cookbook Forum
        * Assignment: 1
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Nov 6th 2021
        * Description: This file is to implement Meal service contains some abstract methods.
*/
package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Meal;
import gbc.comp3095.assignment1.models.Recipe;
import gbc.comp3095.assignment1.models.User;
import gbc.comp3095.assignment1.repositories.MealRepository;
import gbc.comp3095.assignment1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private UserRepository userRepository;

    public void save(Meal meal) {
        Set<Recipe> recipes = meal.getRecipes();
        meal.setRecipes(recipes);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String s = authentication.getName();
        User user = userRepository.findByEmail(s);
        meal.setUser(user);
        mealRepository.save(meal);
        System.out.println(meal.getRecipes());
    }

    @Override
    public Meal findByName(String name) {
        return mealRepository.findByName(name);
    }
    @Override
    public Meal findByUserEmail(String email) { return mealRepository.findByUserEmail(email); }
}
