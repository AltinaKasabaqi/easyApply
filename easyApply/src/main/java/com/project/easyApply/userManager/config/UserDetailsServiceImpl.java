package com.project.easyApply.userManager.config;

import com.project.easyApply.userManager.model.User;
import com.project.easyApply.userManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User u=userRepo.findByEmail(email);
        if(u!=null){
            return new CustomUserDetails(u);

        }
        throw new UsernameNotFoundException("User not found");
    }
}
