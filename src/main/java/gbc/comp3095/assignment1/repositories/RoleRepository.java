/*
Project: Cookbook Forum
        * Assignment: 1
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Nov 6th 2021
        * Description: This file is Role repository, extend jpa repository but not been used yet.
*/
package gbc.comp3095.assignment1.repositories;

import gbc.comp3095.assignment1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
