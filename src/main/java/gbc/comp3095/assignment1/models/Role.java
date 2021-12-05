/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to define Role model but have not been used yet.
*/
package gbc.comp3095.assignment1.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany()
    private Set<User> users = new HashSet<>();
    @JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Users
    public Set<User> getUser() {
        return users;
    }

    public void setUser(Set<User> users) {
        this.users = users;
    }

    //Constructors
    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
