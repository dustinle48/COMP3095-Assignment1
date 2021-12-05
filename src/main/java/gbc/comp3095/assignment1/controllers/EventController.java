/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to set controller of Event
*/
package gbc.comp3095.assignment1.controllers;

import gbc.comp3095.assignment1.models.Event;
import gbc.comp3095.assignment1.models.User;
import gbc.comp3095.assignment1.repositories.EventRepository;
import gbc.comp3095.assignment1.repositories.UserRepository;
import gbc.comp3095.assignment1.services.EventService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@Controller
public class EventController {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    private final EventService eventService;

    public EventController(EventRepository eventRepository, UserRepository userRepository, EventService eventService) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public String getEvents(Model model, @Param("keyword") String keyword, Principal principal) {
        Set<Event> e = eventService.listAll(keyword);
        model.addAttribute("events", e);
        model.addAttribute("keyword", keyword);
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "events/events";
    }

    @GetMapping("/events/create")
    public String getEventCreate(Model model, Principal principal) {
        model.addAttribute("event", new Event());
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "events/create";
    }

    @PostMapping("/events/create")
    public String postEventCreate(@ModelAttribute("event") Event event, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {
            return "events/create";
        }
        User user = userRepository.findByEmail(principal.getName());
        eventService.save(event, user);
        return "redirect:/events";
    }

    @GetMapping("/events/{id}/edit")
    public String getEventEdit(Model model, @PathVariable long id, Principal principal) {
        Event e = eventRepository.findById(id).orElseThrow();
        model.addAttribute("event", e);
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "events/edit";
    }

    @PostMapping("/events/{id}/edit")
    public String postEventEdit(@PathVariable Long id, Event event) {
        eventRepository.editEvent(id, event.getName(), event.getDescription());
        return "redirect:/events";
    }

    @PostMapping("/events/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        eventRepository.deleteEventById(id);
        return "redirect:/events";
    }
}
