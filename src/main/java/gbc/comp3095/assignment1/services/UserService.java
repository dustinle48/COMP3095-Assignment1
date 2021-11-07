/*
Project: Cookbook Forum
        * Assignment: 1
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Nov 6th 2021
        * Description: This file is User service interface.
*/
package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.User;

public interface UserService {
    User findByEmail(String email);
    void save(User user);
}
