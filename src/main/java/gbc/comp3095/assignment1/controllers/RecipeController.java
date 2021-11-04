package gbc.comp3095.assignment1.controllers;

import gbc.comp3095.assignment1.models.Recipe;
import gbc.comp3095.assignment1.repositories.UserRepository;
import gbc.comp3095.assignment1.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import gbc.comp3095.assignment1.repositories.RecipeRepository;

import java.security.Principal;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public RecipeController(RecipeService recipeService, RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/recipes")
    public String getRecipes(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes/recipes";
    }

    @GetMapping("/recipes/create")
    public String getRecipesCreate(Model model, Principal principal) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "recipes/create";
    }

    @PostMapping("/recipes/create")
    public String postRecipesCreate(@ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "recipes/create";
        }
        recipeService.save(recipe);
        return "recipes/recipes";
    }
}
