/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to define User model.
*/
package gbc.comp3095.assignment1.models;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "USER")
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<Recipe> recipes;

    @OneToMany(mappedBy = "user")
    private Set<Meal> meals;

    @ManyToMany()
    private Set<Ingredient> cart;
    @JoinTable(name = "user_ingredient", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))

    @ManyToMany()
    private Set<Recipe> favorite;
    @JoinTable(name = "cart", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "recipe_id"))

    //Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Username
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Firstname
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //Lastname
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //Recipe
    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    //Meal
    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    //Cart
    public Set<Ingredient> getCart() { return cart; }

    public void setCart(Set<Ingredient> cart) { this.cart = cart; }

    //Favorite
    public Set<Recipe> getFavorite() { return favorite; }

    public void setFavorite(Set<Recipe> favorite) { this.favorite = favorite; }

    //Role
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    //Constructors
    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String password) {
        this.password = password;
    }

    public User(String email, String userName, String firstName, String lastName) {
        this.email = email;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email, String password, String userName, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email, String password, String userName, String firstName, String lastName, Set<Ingredient> cart) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cart = cart;
    }

    public User(String email, String password, String userName, String firstName, String lastName, Set<Ingredient> cart, Set<Recipe> favorite) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cart = cart;
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + userName + '\'' +
                ", email=" + email +
                '}';
    }
}
