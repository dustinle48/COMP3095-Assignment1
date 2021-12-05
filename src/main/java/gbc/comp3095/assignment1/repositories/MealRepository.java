/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is Meal repository, extend jpa repository and has some finding functions.
*/
package gbc.comp3095.assignment1.repositories;

import gbc.comp3095.assignment1.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Meal findByName(String name);
    Meal findByUserEmail(String email);

    @Query("SELECT r FROM Meal r WHERE r.user.userName LIKE %?1%")
    public Set<Meal> findByUsername(String userName);

    @Query("SELECT r FROM Meal r WHERE r.name LIKE %?1%"
            + " OR r.description LIKE %?1%"
            + " OR r.user.userName LIKE %?1%")
    public Set<Meal> search(String keyword);
}
