package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Meal;

public interface MealService {
    Meal findByName(String name);
    Meal findByUserEmail(String email);
    void save(Meal meal);
}
