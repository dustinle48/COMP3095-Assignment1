/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is Ingredient service interface.
*/
package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Ingredient;

import java.util.Set;

public interface IngredientService {
    Ingredient findByName(String name);

    Set<Ingredient> listAll(String keyword);
    void save(Ingredient ingredient);
}
