/*
Project: Cookbook Forum
        * Assignment: 1
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Nov 6th 2021
        * Description: This file is Meal repository, extend jpa repository.
*/
package gbc.comp3095.assignment1.repositories;

import gbc.comp3095.assignment1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
