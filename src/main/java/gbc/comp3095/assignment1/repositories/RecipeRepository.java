package gbc.comp3095.assignment1.repositories;

import gbc.comp3095.assignment1.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByName(String name);
}
