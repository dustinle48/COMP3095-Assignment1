/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to set controller of Meal.
*/
package gbc.comp3095.assignment1.controllers;

import gbc.comp3095.assignment1.models.Meal;
import gbc.comp3095.assignment1.models.User;
import gbc.comp3095.assignment1.repositories.MealRepository;
import gbc.comp3095.assignment1.repositories.RecipeRepository;
import gbc.comp3095.assignment1.repositories.UserRepository;
import gbc.comp3095.assignment1.services.MealService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Set;

@Controller
public class MealController {
    private final MealService mealService;

    private final MealRepository mealRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public MealController(MealService mealService, MealRepository mealRepository, RecipeRepository recipeRepository, UserRepository userRepository) {
        this.mealService = mealService;
        this.mealRepository = mealRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/meals")
    public String getMeals(Model model, @Param("keyword") String keyword, Principal principal) {
        Set<Meal> m = mealService.listAll(keyword);
        model.addAttribute("meals", m);
        model.addAttribute("keyword", keyword);
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "meals/meals";
    }

    @GetMapping("/meals/{name}")
    public String getMeal(Model model, @PathVariable String name, Principal principal) {
        model.addAttribute("meal", mealRepository.findByName(name));
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "meals/name";
    }

    @GetMapping("/meals/create")
    public String getMealsCreate(Model model, Principal principal) {
        model.addAttribute("meal", new Meal());
        model.addAttribute("recipes", recipeRepository.findAll());
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "meals/create";
    }

    @PostMapping("/meals/create")
    public String postMeals(@ModelAttribute("meal") Meal meal, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "meals/meals";
        }
        User user = userRepository.findByEmail(principal.getName());
        mealService.save(meal, user);
        return "redirect:/meals";
    }

    @GetMapping("/meals/{id}/edit")
    public String getMealEdit(@PathVariable Long id, Model model, Principal principal) {
        Meal meal = mealRepository.getById(id);
        model.addAttribute("meal", meal);
        model.addAttribute("recipes", recipeRepository.findAll());
        model.addAttribute("user",userRepository.findByEmail(principal.getName()));
        return "meals/edit";
    }

    @PostMapping("/meals/{id}/edit")
    public String postMealEdit(Meal meal, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        mealService.save(meal, user);
        return "redirect:/profile";
    }
}
