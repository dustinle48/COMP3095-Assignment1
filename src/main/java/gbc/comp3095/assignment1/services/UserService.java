package gbc.comp3095.assignment1.services;

import gbc.comp3095.assignment1.models.User;

import java.util.Optional;

public interface UserService {
    User findByEmail(String email);
    void save(User user);
}
