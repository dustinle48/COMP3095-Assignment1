package gbc.comp3095.assignment1.repositories;

import gbc.comp3095.assignment1.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByName(String name);

    @Query("SELECT r FROM Ingredient r WHERE r.name LIKE %?1%"
            + " OR r.description LIKE %?1%")
    public Set<Ingredient> search(String keyword);
}
