/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to define Meal model.
*/
package gbc.comp3095.assignment1.models;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "MEAL")
@DynamicUpdate
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "recipe_meal", joinColumns = @JoinColumn(name = "meal_id"), inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipes = new HashSet<>();

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //Id
    public Long getId() { return id; };

    public void setId(Long id) { this.id = id; }

    //Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Description
    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Meal() {
    }

    public Meal(String name) {
        this.name = name;
    }

    public Meal(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Meal(String name, User user, String description) {
        this.name = name;
        this.user = user;
        this.description = description;
    }

    public Meal(String name, User user, String description, Set<Recipe> recipes) {
        this.name = name;
        this.user = user;
        this.description = description;
        this.recipes = recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(id, meal.id);
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
                ", description='" + description + '\'' +
                ", author=" + user + '\'' +
                ", recipes=" + recipes +
                '}';
    }
}
