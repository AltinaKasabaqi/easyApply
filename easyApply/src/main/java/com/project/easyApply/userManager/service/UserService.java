package com.project.easyApply.userManager.service;

import com.project.easyApply.userManager.model.User;

import java.util.Optional;

public interface UserService {

    public User createUser(User user);

    public boolean checkEmail (String email);

    public int findUserIdByEmail(String email);


}
