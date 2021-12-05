package gbc.comp3095.assignment1.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "FAVORITE")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany()
    private Set<Recipe> recipes = new HashSet<>();
    @JoinTable(name = "favorite_recipe",joinColumns = @JoinColumn(name = "favorite_id"), inverseJoinColumns = @JoinColumn(name = "recipe_id"))

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

    //Recipe
    public Set<Recipe> getRecipes() { return recipes; }

    public void setRecipes(Set<Recipe> recipes) { this.recipes = recipes; }

    public Favorite() {}

    public Favorite(String name, User user) {
        this.name = name;
    }

    public Favorite(String name, User user, Set<Recipe> recipes) {
        this.name = name;
        this.recipes = recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return Objects.equals(id, favorite.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", recipes=" + recipes +
                '}';
    }
}
