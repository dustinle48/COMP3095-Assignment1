/*
Project: Cookbook Forum
        * Assignment: 1
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Nov 6th 2021
        * Description: This file is to set controller of Recipe.
*/
package gbc.comp3095.assignment1.controllers;

import gbc.comp3095.assignment1.models.Recipe;
import gbc.comp3095.assignment1.repositories.UserRepository;
import gbc.comp3095.assignment1.services.RecipeService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import gbc.comp3095.assignment1.repositories.RecipeRepository;

import java.security.Principal;
import java.util.Set;

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
    public String getRecipes(Model model, @Param("keyword") String keyword) {
        Set<Recipe> r = recipeService.listAll(keyword);
        model.addAttribute("recipes", r);
        model.addAttribute("keyword", keyword);
        return "recipes/recipes";
    }

    @GetMapping("/recipes/{name}")
    public String getRecipe(Model model, @PathVariable String name) {
        model.addAttribute("recipe",recipeRepository.findByName(name));
        return "recipes/name";
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
