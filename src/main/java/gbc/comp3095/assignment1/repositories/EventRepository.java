/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is Event repository, extend jpa repository and has some finding and editing functions.
*/
package gbc.comp3095.assignment1.repositories;

import gbc.comp3095.assignment1.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Set;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByName(String name);

    @Query("SELECT e FROM Event e WHERE e.user.userName LIKE %?1%")
    public Set<Event> findByUsername(String userName);

    @Query("SELECT e FROM Event e WHERE e.name LIKE %?1%"
            + " OR e.description LIKE %?1%"
            + " OR e.user.userName LIKE %?1%")
    public Set<Event> search(String keyword);

    @Modifying
    @Transactional
    @Query("UPDATE Event e SET e.name = :name, e.description = :description WHERE e.id = :id")
    public void editEvent(Long id, String name, String description);

    @Modifying
    @Transactional
    @Query("DELETE FROM Event i WHERE i.id = :id")
    public void deleteEventById(Long id);
}
