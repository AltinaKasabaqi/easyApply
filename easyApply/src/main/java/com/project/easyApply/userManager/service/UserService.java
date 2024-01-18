package com.project.easyApply.userManager.service;

import com.project.easyApply.userManager.model.User;

public interface UserService {

    public User createUser(User user);

    public boolean checkEmail (String email);


}
