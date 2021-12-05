/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to handle mailing system.
*/
package gbc.comp3095.assignment1.controllers;

import gbc.comp3095.assignment1.models.User;
import gbc.comp3095.assignment1.repositories.UserRepository;
import gbc.comp3095.assignment1.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.InvalidParameterException;

@Controller
public class MailController {
    private final MailService mailService;

    private final UserRepository userRepository;

    public MailController(MailService mailService, UserRepository userRepository) {
        this.mailService = mailService;
        this.userRepository = userRepository;
    }

    @Autowired
    JavaMailSender mailSender;

    @GetMapping("/login/forgotpassword")
    public String getForgotPassword(Model model, String email) {
        model.addAttribute("email",email);
        return "login/forgotpassword";
    }

    @PostMapping("/login/forgotpassword")
    public String postForgotPassword(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            mailService.sendMail(user.getEmail(),user.getId());
            return "redirect:/login";
        } else {
            return "redirect:/login/forgotpassword?error";
        }
    }
}
