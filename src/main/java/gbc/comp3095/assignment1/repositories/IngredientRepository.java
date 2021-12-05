/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is Ingredient repository, extend jpa repository and has some finding and editing functions.
*/
package gbc.comp3095.assignment1.repositories;

import gbc.comp3095.assignment1.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Set;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByName(String name);

    @Query("SELECT r FROM Ingredient r WHERE r.name LIKE %?1%"
            + " OR r.description LIKE %?1%")
    public Set<Ingredient> search(String keyword);

    @Modifying
    @Transactional
    @Query("DELETE FROM Ingredient i WHERE i.id = :id")
    public void deleteIngredientById(Long id);
}
