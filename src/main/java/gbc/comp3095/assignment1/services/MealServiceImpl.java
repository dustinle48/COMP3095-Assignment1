package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Meal;
import gbc.comp3095.assignment1.models.Recipe;
import gbc.comp3095.assignment1.models.User;
import gbc.comp3095.assignment1.repositories.MealRepository;
import gbc.comp3095.assignment1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private UserRepository userRepository;

    public void save(Meal meal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String s = authentication.getName();
        User user = userRepository.findByEmail(s);
        meal.setUser(user);

        mealRepository.save(meal);
    }

    @Override
    public Meal findByName(String name) {
        return mealRepository.findByName(name);
    }
    @Override
    public Meal findByUserEmail(String email) { return mealRepository.findByUserEmail(email); }
}
