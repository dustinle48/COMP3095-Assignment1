package gbc.comp3095.assignment1.controllers;

import gbc.comp3095.assignment1.models.Ingredient;
import gbc.comp3095.assignment1.repositories.IngredientRepository;
import gbc.comp3095.assignment1.services.IngredientService;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

public class IngredientController {
    private final IngredientRepository ingredientRepository;

    private final IngredientService ingredientService;

    public IngredientController(IngredientRepository ingredientRepository, IngredientService ingredientService) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredients")
    public String getIngredients(Model model, @Param("keyword") String keyword) {
        Set<Ingredient> r = ingredientService.listAll(keyword);
        model.addAttribute("ingredients", r);
        model.addAttribute("keyword", keyword);
        return "ingredients/ingredients";
    }

    @GetMapping("/ingredients/{name}")
    public String getIngredient(Model model, @PathVariable String name) {
        model.addAttribute("ingredient", ingredientRepository.findByName(name));
        return "ingredients/name";
    }
}
