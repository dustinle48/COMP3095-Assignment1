/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to bootstrap some sample data.
*/
package gbc.comp3095.assignment1.bootstrap;

import gbc.comp3095.assignment1.models.*;
import gbc.comp3095.assignment1.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BootstrapData implements CommandLineRunner {
    private UserRepository userRepository;
    private RecipeRepository recipeRepository;
    private MealRepository mealRepository;
    private IngredientRepository ingredientRepository;
    private EventRepository eventRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public BootstrapData(UserRepository userRepository, RecipeRepository recipeRepository, MealRepository mealRepository, IngredientRepository ingredientRepository, EventRepository eventRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.mealRepository = mealRepository;
        this.ingredientRepository = ingredientRepository;
        this.eventRepository = eventRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Ingredient egg = new Ingredient("egg","Brown ones should be better");
        Ingredient rice = new Ingredient("rice","Vietnamese and Thai rice is the best in the world");
        Ingredient milk = new Ingredient("milk", "Any kind of milk is good.");
        ingredientRepository.save(egg);
        ingredientRepository.save(rice);
        ingredientRepository.save(milk);

        Set<Ingredient> i = Set.of(egg,rice);
        User thinh = new User("ducthinh481994@gmail.com", passwordEncoder.encode("123"),"Dustin","Thinh","Le",i);
        userRepository.save(thinh);

        Set<Ingredient> efr = Set.of(egg,rice);
        Set<Ingredient> c = Set.of(egg,milk);
        Recipe egg_fried_rice = new Recipe("egg fried rice",thinh,"fry rice with egg",efr);
        Recipe cake = new Recipe("cake", thinh, "make cake with milk and egg",c);
        recipeRepository.save(egg_fried_rice);
        recipeRepository.save(cake);

        Set<Recipe> s = Set.of(egg_fried_rice,cake);
        Meal breakfast = new Meal("breakfast", thinh, "Simple meal for breakfast",s);
        mealRepository.save(breakfast);

        Event xmas = new Event("xmas","merry xmas",thinh);
        eventRepository.save(xmas);
    }
}
