package com.project.easyApply.userManager.controller;


import com.project.easyApply.userManager.model.User;
import com.project.easyApply.userManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @ModelAttribute
    private void userDetails(Model m, Principal p){
        System.out.println("Executing userDetails method...");
        String email=p.getName();
        User u =userRepo.findByEmail(email);
        System.out.println(u);
        m.addAttribute("user",u);
    }



        @GetMapping("/")
    public String home(){
        return "user/dashboard";
    }

    @GetMapping("/profile")
    public String profile(){
        return "user/profile";

    }
}


