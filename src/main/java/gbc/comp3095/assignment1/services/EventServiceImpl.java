/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to implement Event service contains some abstract methods.
*/
package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.Event;
import gbc.comp3095.assignment1.models.User;
import gbc.comp3095.assignment1.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    public void save(Event event, User user) {
        event.setUser(user);
        eventRepository.save(event);
    }

    public Set<Event> listAll(String keyword) {
        if (keyword != null) {
            return eventRepository.search(keyword);
        }
        Set<Event> e = new HashSet<>(eventRepository.findAll());
        return e;
    }

    @Override
    public Event findByName(String name) {
        return eventRepository.findByName(name);
    }
}
