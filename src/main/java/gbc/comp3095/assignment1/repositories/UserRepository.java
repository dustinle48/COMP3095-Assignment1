package gbc.comp3095.assignment1.repositories;

import gbc.comp3095.assignment1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
