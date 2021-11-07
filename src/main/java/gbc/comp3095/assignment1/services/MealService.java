/*
Project: Cookbook Forum
        * Assignment: 1
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Nov 6th 2021
        * Description: This file is Meal service interface.
*/
package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Meal;

public interface MealService {
    Meal findByName(String name);
    Meal findByUserEmail(String email);
    void save(Meal meal);
}
