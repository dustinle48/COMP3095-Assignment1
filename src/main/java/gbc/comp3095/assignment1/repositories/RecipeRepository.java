/*
Project: Cookbook Forum
        * Assignment: 1
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Nov 6th 2021
        * Description: This file is Recipe repository, extend jpa repository and has some finding functions.
*/
package gbc.comp3095.assignment1.repositories;

import gbc.comp3095.assignment1.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByName(String name);

    @Query("SELECT r FROM Recipe r WHERE r.user.userName LIKE %?1%")
    public Set<Recipe> findByUsername(String userName);

    @Query("SELECT r FROM Recipe r WHERE r.name LIKE %?1%"
            + " OR r.description LIKE %?1%"
            + " OR r.user.userName LIKE %?1%")
    public Set<Recipe> search(String keyword);
}
