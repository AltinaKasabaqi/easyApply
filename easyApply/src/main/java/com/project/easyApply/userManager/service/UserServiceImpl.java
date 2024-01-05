package com.project.easyApply.userManager.service;


import com.project.easyApply.userManager.model.UserDetails;
import com.project.easyApply.userManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails createUser(UserDetails user) {
        return userRepo.save(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepo.existsByEmail(email);
    }

}
