package gbc.comp3095.assignment1.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CART")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany()
    private Set<Ingredient> ingredients = new HashSet<>();
    @JoinTable(name = "cart_ingredient", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))

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

    //Ingredient
    public Set<Ingredient> getIngredients() { return ingredients; }

    public void setIngredients(Set<Ingredient> ingredients){ this.ingredients = ingredients; }

    public Cart() {}

    public Cart(String name) {
        this.name = name;
    }

    public Cart(String name, User user) {
        this.name = name;
    }

    public Cart(String name, User user, Set<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
