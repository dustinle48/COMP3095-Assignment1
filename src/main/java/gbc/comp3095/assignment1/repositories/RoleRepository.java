package gbc.comp3095.assignment1.repositories;

import gbc.comp3095.assignment1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
