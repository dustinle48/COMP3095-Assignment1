/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is Meal repository, extend jpa repository.
*/
package gbc.comp3095.assignment1.repositories;

import gbc.comp3095.assignment1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email = :email, u.userName = :userName, u.lastName = :lastName, u.firstName = :firstName WHERE u.id = :id")
    public void editProfile(Long id, String email, String userName, String lastName, String firstName);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    public void changePassword(Long id, String password);
}
