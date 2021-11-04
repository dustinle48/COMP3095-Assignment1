package gbc.comp3095.assignment1.controllers;

import gbc.comp3095.assignment1.models.Meal;
import gbc.comp3095.assignment1.models.Recipe;
import gbc.comp3095.assignment1.repositories.MealRepository;
import gbc.comp3095.assignment1.repositories.RecipeRepository;
import gbc.comp3095.assignment1.repositories.UserRepository;
import gbc.comp3095.assignment1.services.MealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

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
    public String getMeals(Model model) {
        model.addAttribute("meals", mealRepository.findAll());
        return "meals/meals";
    }

    @GetMapping("/meals/create")
    public String getMealsCreate(Model model, Principal principal) {
        model.addAttribute("meal", new Meal());
        model.addAttribute("recipes", recipeRepository.findAll());
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "meals/create";
    }

    @PostMapping("/meals/create")
    public String postMeals(@ModelAttribute("meal") Meal meal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "meals/meals";
        }
        mealService.save(meal);
        return "meals/meals";
    }
}
