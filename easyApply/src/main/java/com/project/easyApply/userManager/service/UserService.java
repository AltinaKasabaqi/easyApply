package com.project.easyApply.userManager.service;

import com.project.easyApply.userManager.model.UserDetails;
import com.project.easyApply.userManager.repository.UserRepository;

public interface UserService {

    public UserDetails createUser(UserDetails user);

    public boolean checkEmail (String email);


}
