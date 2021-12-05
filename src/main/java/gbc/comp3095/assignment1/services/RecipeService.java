/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is Recipe service interface.
*/
package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Recipe;
import gbc.comp3095.assignment1.models.User;

import java.util.Set;

public interface RecipeService {
    Recipe findByName(String name);

    Set<Recipe> listAll(String keyword);
    void save(Recipe recipe, User user);
}
