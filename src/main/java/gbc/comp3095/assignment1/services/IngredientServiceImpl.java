/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to implement Ingredient service contains some abstract methods.
*/
package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Ingredient;
import gbc.comp3095.assignment1.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public void save(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public Set<Ingredient> listAll(String keyword) {
        if (keyword != null) {
            return ingredientRepository.search(keyword);
        }
        Set<Ingredient> r = new HashSet<>(ingredientRepository.findAll());
        return r;
    }

    @Override
    public Ingredient findByName(String name) {
        return ingredientRepository.findByName(name);
    }
}
