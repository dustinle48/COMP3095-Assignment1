/*
Project: Cookbook Forum
        * Assignment: 1
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Nov 6th 2021
        * Description: This file is to bootstrap some sample data.
*/
package gbc.comp3095.assignment1.bootstrap;

import gbc.comp3095.assignment1.models.Meal;
import gbc.comp3095.assignment1.models.Recipe;
import gbc.comp3095.assignment1.models.User;
import gbc.comp3095.assignment1.repositories.MealRepository;
import gbc.comp3095.assignment1.repositories.RecipeRepository;
import gbc.comp3095.assignment1.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BootstrapData implements CommandLineRunner {
    private UserRepository userRepository;
    private RecipeRepository recipeRepository;
    private MealRepository mealRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public BootstrapData(UserRepository userRepository, RecipeRepository recipeRepository, MealRepository mealRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.mealRepository = mealRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        User thinh = new User("dt@gmail.com", passwordEncoder.encode("123"),"Dustin","Thinh","Le");
        userRepository.save(thinh);

        Recipe egg = new Recipe("egg",thinh,"Buy some egg");
        Recipe milk = new Recipe("milk", thinh, "Buy some milk");
        recipeRepository.save(egg);
        recipeRepository.save(milk);

        Set<Recipe> s = Set.of(egg,milk);
        Meal breakfast = new Meal("breakfast", thinh, "Simple meal for breakfast",s);
        mealRepository.save(breakfast);
    }
}
