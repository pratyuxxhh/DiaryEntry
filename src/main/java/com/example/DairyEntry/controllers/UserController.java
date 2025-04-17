package com.example.DairyEntry.controllers;


import com.example.DairyEntry.config.AppConfig;
import com.example.DairyEntry.pojos.UserDetails;
import com.example.DairyEntry.services.UserDetailServiceImpl;
import com.example.DairyEntry.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServices userServices;
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @PostMapping("/post-user")
    public ResponseEntity<UserDetails> saveUser(@RequestBody UserDetails user){
        user.setPassword(appConfig.passwordEncoder().encode(user.getPassword()));
        userServices.saveThisUser(user);
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }
    @GetMapping("/user")
    public String getUserName(){
        return userServices.getCurrentUser() ;
    }
    @GetMapping("/check-username")
    public boolean isUsernameAvailable(@RequestParam String username) {
        return userServices.existsByUserName(username);
    }
}
