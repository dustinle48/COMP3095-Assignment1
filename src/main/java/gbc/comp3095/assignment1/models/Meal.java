package gbc.comp3095.assignment1.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
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
}
