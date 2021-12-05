/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is Meal service interface.
*/
package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Meal;
import gbc.comp3095.assignment1.models.User;

import java.util.Set;

public interface MealService {
    Meal findByName(String name);
    Meal findByUserEmail(String email);
    Set<Meal> listAll(String keyword);
    void save(Meal meal, User user);
}
