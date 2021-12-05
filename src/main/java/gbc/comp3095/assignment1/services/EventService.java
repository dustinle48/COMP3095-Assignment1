package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Event;
import gbc.comp3095.assignment1.models.User;

import java.util.Set;

public interface EventService {
    Event findByName(String name);

    Set<Event> listAll(String keyword);
    void save(Event event, User user);
}
