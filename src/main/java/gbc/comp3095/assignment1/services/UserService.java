/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is User service interface.
*/
package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Ingredient;
import gbc.comp3095.assignment1.models.Recipe;
import gbc.comp3095.assignment1.models.User;

public interface UserService {
    User findByEmail(String email);
    void save(User user);
    void update(User user);
    void addCart(User user, Ingredient ingredient);
    void deleteCart(User user, Ingredient ingredient);

    void addFavorite(User user, Recipe recipe);
    void deleteFavorite(User user, Recipe recipe);
}
