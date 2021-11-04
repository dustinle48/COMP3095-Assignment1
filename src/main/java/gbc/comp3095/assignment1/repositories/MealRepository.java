package gbc.comp3095.assignment1.repositories;

import gbc.comp3095.assignment1.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Meal findByName(String name);
    Meal findByUserEmail(String email);
}
