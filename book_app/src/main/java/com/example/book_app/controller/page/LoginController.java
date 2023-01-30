package com.example.book_app.controller.page;

import com.example.book_app.dto.BookDto;
import com.example.book_app.entity.User;
import com.example.book_app.exception.WrongLoginException;
import com.example.book_app.repository.UserRepository;
import com.example.book_app.service.BookService;
import com.example.book_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }

    @PostMapping("/")
    public String loginSubmit(@ModelAttribute User user, Model model) throws WrongLoginException {
        model.addAttribute("user", user);
        if (userService.verifyUser(user.getUsername(), user.getPassword())) {
            return "redirect:mainPage";
        } else {
            throw new WrongLoginException();
        }
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

}
