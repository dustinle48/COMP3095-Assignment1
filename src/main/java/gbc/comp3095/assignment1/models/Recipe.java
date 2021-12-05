/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to define Recipe model.
*/
package gbc.comp3095.assignment1.models;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "RECIPE")
@DynamicUpdate
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private LocalDate time;
    private String description;

    @ManyToOne()
    private User user;
    @JoinTable(name = "recipe_user", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))

    @ManyToMany()
    private Set<Ingredient> ingredients = new HashSet<>();
    @JoinTable(name = "recipe_ingredient", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns =@JoinColumn(name = "ingredient_id"))

    //Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Time
    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    //Description
    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    //User
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //Ingredient
    public Set<Ingredient> getIngredients() { return ingredients; }

    public void setIngredients(Set<Ingredient> ingredients){ this.ingredients = ingredients; }

    public Recipe() {
    }

    public Recipe(String name) {
        this.name = name;
    }

    public Recipe(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Recipe(String name, User user, String description) {
        this.name = name;
        this.user = user;
        this.description = description;
    }
    public Recipe(String name, User user, String description, Set<Ingredient> ingredients) {
        this.name = name;
        this.user = user;
        this.description = description;
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + user +
                '}';
    }
}
