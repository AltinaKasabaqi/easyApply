package com.project.easyApply.userManager.controller;

import com.project.easyApply.userManager.model.User;
import com.project.easyApply.userManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(){
        return "FirstPage";
    }
    @GetMapping("/signin")
    public String login(){
        return "signin";
    }
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
    @GetMapping("/dashboard")
    public String dashboard(){return "dashboard";}

//    @GetMapping ("user/profile")
//    public String Profile() {return "profile";}


@PostMapping("/createUser")
public String createuser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
    boolean f = userService.checkEmail(user.getEmail());

    if (f) {
        redirectAttributes.addFlashAttribute("msg", "Email already exists");
    } else {
        User userDtls = userService.createUser(user);

        if (userDtls != null) {
            redirectAttributes.addFlashAttribute("msg", "Registered");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Something is wrong");
        }
    }

    return "redirect:/signup";
}






}
