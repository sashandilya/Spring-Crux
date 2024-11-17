package com.example.demo.service;

import com.example.demo.dto.RegisterUserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateDataFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;
    public User signup(RegisterUserDto registerUserDto) throws DuplicateDataFoundException {
        User user = userRepository.findUserByEmail(registerUserDto.getEmail()).orElse(null);
        if (user == null) {
            user = new User();
            user.setFullName(registerUserDto.getFullName());
            user.setEmail(registerUserDto.getEmail());
            user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        }
        else{
            throw new DuplicateDataFoundException("Duplicate data.");
        }
        return userRepository.save(user);

    }

}
