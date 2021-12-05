/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is Mailing service interface.
*/
package gbc.comp3095.assignment1.services;

public interface MailService {
    void sendMail(String email, Long id);
}
