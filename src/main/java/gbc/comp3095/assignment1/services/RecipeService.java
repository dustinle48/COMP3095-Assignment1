package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Recipe;

public interface RecipeService {
    Recipe findByName(String name);
    void save(Recipe recipe);
}
