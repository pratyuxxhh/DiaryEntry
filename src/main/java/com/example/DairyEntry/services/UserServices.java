package com.example.DairyEntry.services;

import com.example.DairyEntry.pojos.DiaryPOJO;
import com.example.DairyEntry.pojos.UserDetails;
import com.example.DairyEntry.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserServices {
    private static final Logger log = LoggerFactory.getLogger(UserServices.class);
    @Autowired
    private UserRepo userRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveThisUser(UserDetails user) {
        user.setPassword(user.getPassword());
        userRepo.save(user);
    }

    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        return username;
    }

    public List<UserDetails> getAllUsers() {
        return userRepo.findAll();
    }

    public boolean existsByUserName(String username) {
        return userRepo.existsByUsername(username);
    }

    public List<DiaryPOJO> getUsersDiaries(UserDetails user) {
        List<DiaryPOJO> diaries = user.getDiarylist();
        return diaries;
    }
}
