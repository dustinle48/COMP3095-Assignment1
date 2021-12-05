/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is to prevent user from creating more than 1 account using the same email.
*/
package gbc.comp3095.assignment1.validators;

import gbc.comp3095.assignment1.models.User;
import gbc.comp3095.assignment1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors err) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(err,"email","NotEmpty");
        if (userService.findByEmail(user.getEmail()) != null) {
            err.rejectValue("email","Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(err,"password","NotEmpty");
    }
}
