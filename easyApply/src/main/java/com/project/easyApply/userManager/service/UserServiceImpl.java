package com.project.easyApply.userManager.service;


import com.project.easyApply.userManager.model.User;
import com.project.easyApply.userManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncode;

    @Override
    public User createUser(User user) {
        user.setFjalekalimi(passwordEncode.encode(user.getFjalekalimi()));
        return userRepo.save(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepo.existsByEmail(email);
    }

}
