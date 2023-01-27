package com.example.book_app.controller.entity;

import com.example.book_app.dto.UserDto;
import com.example.book_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public void saveUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
    }
}
