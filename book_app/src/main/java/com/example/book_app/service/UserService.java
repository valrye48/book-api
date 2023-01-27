package com.example.book_app.service;

import com.example.book_app.dto.UserDto;
import com.example.book_app.entity.User;
import com.example.book_app.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean verifyUser(String username, String password) {
            var userFromDb = userRepository.findByUsername(username);
            if (userFromDb != null) {
                return userFromDb.getPassword().equals(password);
            }
            return false;
    }

    public List<UserDto> getAllUsers() {
        var users = userRepository.findAll();
        List<UserDto> result = new ArrayList<>();
        for (User user : users) {
            result.add(new UserDto(user.getId(), user.getUsername(), user.getName(), user.getSurname(),
                    user.getPassword()));
        }
        return result;
    }

    public void saveUser(UserDto userDto) {
        User user = new User(userDto.getUsername(), userDto.getName(), userDto.getSurname(),
                userDto.getPassword());
        userRepository.save(user);
    }

    public void addBooksToUser(UserDto userDto) {
        var user = userRepository.findById(userDto.getId()).orElse(null);
        if (user != null) {
            //TODO: get all books and then add them by user id matching
        } else {
            //TODO:throw an exception (error page)
        }
    }

}
