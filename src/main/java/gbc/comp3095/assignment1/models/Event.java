/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to define Event model.
*/
package gbc.comp3095.assignment1.models;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EVENT")
@DynamicUpdate
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    @ManyToOne()
    private User user;
    @JoinTable(name = "event_user", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))

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

    //Description
    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    //User
    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    //Constructor
    public Event() {};

    public Event(String name) {
        this.name = name;
    }

    public Event(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Event(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EVent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description=" + description +
                '}';
    }
}
