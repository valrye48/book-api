package com.example.book_app.controller.entity;

import com.example.book_app.dto.UserDto;
import com.example.book_app.exception.BookDoesntExistException;
import com.example.book_app.exception.UserDoesntExistException;
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

    @GetMapping("/getById/{id}")
    public UserDto getUserById(@PathVariable Long id) throws UserDoesntExistException {
        return userService.getUserById(id);
    }

    @DeleteMapping("/removeUser/{id}")
    public void removeUser(@PathVariable Long id) throws UserDoesntExistException {
        userService.removeUser(id);
    }

    @GetMapping("/getByBook/{id}")
    public List<UserDto> getByBook(@PathVariable Long id) throws BookDoesntExistException {
        return userService.getUsersByBook(id);
    }
}
