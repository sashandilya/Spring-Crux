package com.example.demo.controller;

import com.example.demo.dto.ErrorRes;
import com.example.demo.dto.LoginReq;
import com.example.demo.dto.LoginRes;
import com.example.demo.dto.RegisterUserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/rest/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Object> register(@RequestBody RegisterUserDto registerUserDto) {
        try {
            User registeredUser = authService.signup(registerUserDto);
            return ResponseEntity.ok(registeredUser);
        }
        catch (Exception e){
            Map<String, String>errMsg = new HashMap<>();
            errMsg.put("Status", "Error");
            errMsg.put("Message", "Duplicate record found.");
            return ResponseEntity.ok(errMsg);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String, Object> loginHandler( @RequestBody LoginReq credentials) {

        UsernamePasswordAuthenticationToken authCredentials = new UsernamePasswordAuthenticationToken(
                credentials.getEmail(), credentials.getPassword());

        authenticationManager.authenticate(authCredentials);

        String token = jwtUtil.generateToken(credentials.getEmail());

        return Collections.singletonMap("jwt-token", token);
    }
}