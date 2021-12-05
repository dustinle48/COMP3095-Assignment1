/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to set controller of Ingredient
*/
package gbc.comp3095.assignment1.controllers;

import gbc.comp3095.assignment1.models.Ingredient;
import gbc.comp3095.assignment1.repositories.IngredientRepository;
import gbc.comp3095.assignment1.repositories.UserRepository;
import gbc.comp3095.assignment1.services.IngredientService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@Controller
public class IngredientController {
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    private final IngredientService ingredientService;

    public IngredientController(IngredientRepository ingredientRepository, UserRepository userRepository, IngredientService ingredientService) {
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredients")
    public String getIngredients(Model model, @Param("keyword") String keyword, Principal principal) {
        Set<Ingredient> r = ingredientService.listAll(keyword);
        model.addAttribute("ingredients", r);
        model.addAttribute("keyword", keyword);
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "ingredients/ingredients";
    }

    @GetMapping("/ingredients/{name}")
    public String getIngredient(Model model, @PathVariable String name, Principal principal) {
        model.addAttribute("ingredient", ingredientRepository.findByName(name));
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "ingredients/name";
    }

    @GetMapping("/ingredients/create")
    public String getIngredientCreate(Model model, Principal principal) {
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "ingredients/create";
    }

    @PostMapping("/ingredients/create")
    public String postIngredientCreate(@ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create";
        }
        ingredientService.save(ingredient);
        return "redirect:/ingredients";
    }
}
