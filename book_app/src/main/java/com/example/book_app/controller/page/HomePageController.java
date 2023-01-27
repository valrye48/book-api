package com.example.book_app.controller.page;

import com.example.book_app.entity.User;
import com.example.book_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomePageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }

    @PostMapping("/")
    public String loginSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        if (userService.verifyUser(user.getUsername(), user.getPassword())) {
            return "mainPage";
        } else {

            //TODO:make an exception or error page for this

            System.out.println("No >:(");
        }
        return "home";
    }

}
