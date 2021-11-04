package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Recipe;
import gbc.comp3095.assignment1.models.User;
import gbc.comp3095.assignment1.repositories.RecipeRepository;
import gbc.comp3095.assignment1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;

    public void save(Recipe recipe) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String s = authentication.getName();
        User user = userRepository.findByEmail(s);
        recipe.setUser(user);
        recipeRepository.save(recipe);
    }

    @Override
    public Recipe findByName(String name) {
        return recipeRepository.findByName(name);
    }

}
