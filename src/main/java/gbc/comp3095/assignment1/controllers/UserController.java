/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to set controller of User.
*/
package gbc.comp3095.assignment1.controllers;

import com.itextpdf.text.DocumentException;
import gbc.comp3095.assignment1.models.Ingredient;
import gbc.comp3095.assignment1.models.User;
import gbc.comp3095.assignment1.repositories.*;
import gbc.comp3095.assignment1.services.ExportService;
import gbc.comp3095.assignment1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final MealRepository mealRepository;
    private final EventRepository eventRepository;
    private final IngredientRepository ingredientRepository;

    private final UserService userService;
    private final ExportService exportService;

    public UserController(UserRepository userRepository, RecipeRepository recipeRepository, MealRepository mealRepository, EventRepository eventRepository, IngredientRepository ingredientRepository, UserService userService, ExportService exportService) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.mealRepository = mealRepository;
        this.eventRepository = eventRepository;
        this.ingredientRepository = ingredientRepository;
        this.userService = userService;
        this.exportService = exportService;

    }

    @GetMapping("/")
    public String getIndex(Model model, Principal principal) {
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "/index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/users";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("recipes", recipeRepository.findByUsername(user.getUserName()));
        model.addAttribute("meals", mealRepository.findByUsername(user.getUserName()));
        model.addAttribute("events", eventRepository.findByUsername(user.getUserName()));
        return "users/profile";
    }

    @GetMapping("/profile/edit")
    public String getProfileEdit(Model model, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("/profile/{id}/edit")
    public String postProfileEdit(User user) {
        userRepository.editProfile(user.getId(),user.getEmail(),user.getUserName(),user.getFirstName(),user.getLastName());
        return "redirect:/profile";
    }

    @GetMapping("/password/edit")
    public String getChangePassword(Model model, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "users/password";
    }

    @PostMapping("/password/{id}/edit")
    public String postChangePassword(@PathVariable Long id, User user) {
        String p = passwordEncoder.encode(user.getPassword());
        userRepository.changePassword(id, p);
        return "redirect:/profile";
    }

    @GetMapping("/resetpassword/{id}")
    public String getResetPassword(@PathVariable Long id, Model model) {
        User user = userRepository.getById(id);
        model.addAttribute("user", user);
        return "login/resetpassword";
    }

    @PostMapping("/resetpassword/{id}")
    public String postResetPassword(@PathVariable Long id, User user) {
        String p = passwordEncoder.encode(user.getPassword());
        userRepository.changePassword(id,p);
        return "redirect:/login";
    }

    @PostMapping("/cart/{id}/add")
    public String addCart(@PathVariable Long id, Principal principal) {
        userService.addCart(userRepository.findByEmail(principal.getName()),ingredientRepository.getById(id));
        return "redirect:/profile";
    }

    @PostMapping("/cart/{id}/delete")
    public String deleteCart(@PathVariable Long id, Principal principal) {
        userService.deleteCart(userRepository.findByEmail(principal.getName()),ingredientRepository.getById(id));
        return "redirect:/profile";
    }

    @GetMapping("/cart/export")
    public void postCartExport(HttpServletResponse response, Principal principal) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        User user = userRepository.findByEmail(principal.getName());
        Set<Ingredient> cart = user.getCart();

        exportService.CartPDFExporter(cart);
        exportService.exportPdf(response);
    }

    @PostMapping("/favorite/{id}/add")
    public String addFavorite(@PathVariable Long id, Principal principal) {
        userService.addFavorite(userRepository.findByEmail(principal.getName()),recipeRepository.getById(id));
        return "redirect:/profile";
    }

    @PostMapping("/favorite/{id}/delete")
    public String deleteFavorite(@PathVariable Long id, Principal principal) {
        userService.deleteFavorite(userRepository.findByEmail(principal.getName()),recipeRepository.getById(id));
        return "redirect:/profile";
    }
}
