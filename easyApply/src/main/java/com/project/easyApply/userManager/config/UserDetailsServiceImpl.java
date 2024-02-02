package com.project.easyApply.userManager.config;

import com.project.easyApply.userManager.model.Kompania;
import com.project.easyApply.userManager.repository.KompaniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private KompaniaRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Kompania u=userRepo.findByEmail(email);
        if(u!=null){
            return new CustomUserDetails(u);

        }
        throw new UsernameNotFoundException("User not found");
    }
}
