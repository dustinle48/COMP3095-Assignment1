/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Nov 6th 2021
        * Description: This file is Meal service interface.
*/
package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Ingredient;

import java.util.Set;

public interface IngredientService {
    Ingredient findByName(String name);

    Set<Ingredient> listAll(String keyword);
    void save(Ingredient ingredient);
}
