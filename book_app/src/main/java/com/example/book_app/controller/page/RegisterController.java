package com.example.book_app.controller.page;

import com.example.book_app.dto.UserDto;
import com.example.book_app.entity.User;
import com.example.book_app.exception.UsernameExistsException;
import com.example.book_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("newUser", new User());
        return "registerPage";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute User newUser, Model model) throws UsernameExistsException {
        model.addAttribute("newUser", newUser);
        if (!userService.existsByUsername(newUser.getUsername())) {
            userService.saveUser(new UserDto(newUser.getUsername(),
                    newUser.getName(), newUser.getSurname(), newUser.getPassword()));
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", "A user with this username already exists.");
        }
        return "registerPage";
    }
}
