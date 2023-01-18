package com.example.book_app.service;

import com.example.book_app.entity.User;
import com.example.book_app.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class UserService {

    private UserRepository userRepository;

    public boolean checkIfUserExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
