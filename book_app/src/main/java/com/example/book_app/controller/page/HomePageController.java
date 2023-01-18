package com.example.book_app.controller.page;

import com.example.book_app.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }

    @PostMapping("/")
    public String loginSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        System.out.println(user.toString());
        return "home";
    }


}
